package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.repository.CaseDocumentsRepository;
import com.nscs.SBMaster.repository.CommonRepository;
import com.nscs.SBMaster.service.CaseDetailsService;
import com.nscs.SBMaster.service.CaseDocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Controller
public class DocumentController {
    @Autowired
    private CaseDetailsService caseDetailsService;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CaseDocumentsRepository caseDocumentsRepository;

    @Autowired
    private CaseDocumentsService caseDocumentsService;

    @Value("${file.upload-dir}")
    private String baseUploadDir;

    @Value("${factfile.upload-dir}")
    private String factUploadDir;

    @GetMapping("/getFilePath/{fileId}")
    public ResponseEntity<String> getFilePath(@PathVariable Long fileId) {
        try{
            String filePath = caseDocumentsService.getFilePathByFileId(fileId);
            if (filePath == null) {
                throw new RuntimeException("File path not found for caseId: " + fileId);
            }
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            byte[] fileContent = Files.readAllBytes(path);
            String base64File = Base64.getEncoder().encodeToString(fileContent);
            String fileExtension = getFileExtension(path.getFileName().toString());

            String response = String.format("{\"fileExtension\":\"%s\",\"fileContent\":\"%s\"}", fileExtension, base64File);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }

    }

//    private File convertToPdf(Path docPath) throws Exception {
//        File pdfFile = new File(docPath.toString() + ".pdf");
//        Document doc = new Document(docPath.toString());
//        doc.save(pdfFile.getAbsolutePath(), SaveFormat.PDF);
//        return pdfFile;
//    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex != -1) ? fileName.substring(dotIndex + 1).toLowerCase() : "";
    }
}

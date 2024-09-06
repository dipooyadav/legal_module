package com.nscspl.nkss.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nscspl.nkss.entity.MenuAccess;
import com.nscspl.nkss.repository.MenuAccessRepository;
import com.nscspl.nkss.service.MenuAccessService;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuAccessController {
    @Autowired
    private MenuAccessRepository MenuAccessRepository;
    @Autowired
    MenuAccessService menuAccessService;

    @PostMapping({"/getMenuAccess"})
    public List<MenuAccess> getMenuAccess() {
        return this.menuAccessService.findAll();
    }

    @PostMapping({"/getMenu"})
    public ResponseEntity<?> getMenu() {
        return ResponseEntity.ok(this.MenuAccessRepository.findAllMenuByIdAndLevel());
    }

    //@GetMapping({"/menu/findByDesignationID/{ustid}"})
    @GetMapping(value = "/menu/acessright/{ustid}", produces = "application/json")
    public ResponseEntity<String> getAccessRightMenuUst(@PathVariable String ustid) {
        StringBuilder strBuild = new StringBuilder("");
        String[][] sreturnData = (String[][]) null;
        try {
            sreturnData = this.MenuAccessRepository.getAccessRightMenuUst(ustid);
            if (sreturnData != null) {
                strBuild.append("[");
                for (int j = 0; j < sreturnData.length; j++) {
                    if (BMDouble.parseLong(sreturnData[j][4]) > 1000L) {
                        if (j == sreturnData.length - 1) {
                            if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                                strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            } else {
                                strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            }
                        } else if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id : " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        }
                    } else if (BMDouble.parseLong(sreturnData[j][4]) < 1000L) {
                        if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        }
                        strBuild = getAccessRightMenuRecursiveUst(ustid, sreturnData[j][2] + sreturnData[j][3], strBuild);
                    }
                }
                int endpos = strBuild.length();
                strBuild.replace(endpos - 1, endpos, "]");
                strBuild.replace(endpos - 4, endpos - 3, " ");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return ResponseEntity.ok(strBuild.toString());
    }

    private StringBuilder getAccessRightMenuRecursiveUst(String ustid, String data, StringBuilder strBuild) {
        String[][] sreturnData11 = (String[][]) null;
        try {
            data = data.replaceAll("\\s", "");

            sreturnData11 = this.MenuAccessRepository.findDistinctByLvlnoAndActiveAndMnuApplication(data, ustid);
            if (sreturnData11 == null) {
                System.out.println(strBuild.toString());
                int endpos = strBuild.length();
                strBuild.replace(endpos - 2, endpos, "},");
                System.out.println(strBuild.toString());
                return strBuild;
            }
            strBuild.append("children:[ ");
            for (int j = 0; j < sreturnData11.length; j++) {
                if (BMDouble.parseLong(sreturnData11[j][4]) > 1000L) {
                    if (j == sreturnData11.length - 1) {
                        if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "} ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] +", value: " + sreturnData11[j][7] + "} ");
                        }
                    } else if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    } else {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false, id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    }
                } else if (BMDouble.parseLong(sreturnData11[j][4]) < 1000L) {
                    if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\", checked: true,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    } else {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    }
                    strBuild = getAccessRightMenuRecursiveUst(ustid, sreturnData11[j][2] + sreturnData11[j][3], strBuild);
                }
            }
            strBuild.append("]},");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuild;
    }

    @PostMapping(value = "/updateAcessRight", produces = "application/json")
    public ResponseEntity<?> saveStatus(@RequestBody String data) throws JSONException {
        try {
            Gson gson = new Gson();
            String message = "Failed";
            String Status = "Failed";
            String Result = "Failed";
            JSONObject response1 = null;
            response1 = new JSONObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            String desg = jsonNode.get("desg").asText();
            JsonNode menusNode = jsonNode.get("menus");
            if (menusNode.isArray()) {
                for (JsonNode menuNode : menusNode) {
                    String id = menuNode.get("id").asText();
                    String status = menuNode.get("status").asText();
                    if (status.equals("true")) {
                        MenuAccessRepository.updateAccessRightTrue(desg, id);
                    } else {
                        MenuAccessRepository.updateAccessRightFalse(desg, id);
                    }

                }
            }

            Status = "OK";
            message = "Successfully updated";
            Result = "Success";

            response1.put("Status", Status);
            response1.put("message", message);
            response1.put("Result", Result);
            //return response1.toString();
            return (ResponseEntity<?>) ResponseEntity.ok().body(response1.toString());
        } catch (NullPointerException e) {
            String errorMessage = "Null pointer exception occurred. Please check your request parameters.";
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @PostMapping(value = "/menu/findByDesignationID/{ustid}", produces = "application/json")
    public ResponseEntity<String> getAccessRightMenuUstAR(@PathVariable String ustid) {
        StringBuilder strBuild = new StringBuilder("");
        String[][] sreturnData = (String[][]) null;
        JSONObject finalResponse = new JSONObject();
        try {
            sreturnData = this.MenuAccessRepository.getAccessRightMenuUstAR(ustid);
            if (sreturnData != null) {
                strBuild.append("[");
                for (int j = 0; j < sreturnData.length; j++) {
                    if (BMDouble.parseLong(sreturnData[j][4]) > 1000L) {
                        if (j == sreturnData.length - 1) {
                            if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                                strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            } else {
                                strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            }
                        } else if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id : " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        }
                    } else if (BMDouble.parseLong(sreturnData[j][4]) < 1000L) {
                        if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        }
                        strBuild = getAccessRightMenuRecursiveUstAR(ustid, sreturnData[j][2] + sreturnData[j][3], strBuild);
                    }
                }
                int endpos = strBuild.length();
                strBuild.replace(endpos - 1, endpos, "]");
                strBuild.replace(endpos - 4, endpos - 3, " ");
            }
            String result=strBuild.toString();
            JSONArray jsonArray = new JSONArray(result);
            finalResponse.put("menuData", jsonArray);
        } catch (Exception x) {
            x.printStackTrace();
        }
        return ResponseEntity.ok(finalResponse.toString());
    }

    private StringBuilder getAccessRightMenuRecursiveUstAR(String ustid, String data, StringBuilder strBuild) {
        String[][] sreturnData11 = (String[][]) null;
        try {
            data = data.replaceAll("\\s", "");

            sreturnData11 = this.MenuAccessRepository.findDistinctByLvlnoAndActiveAndMnuApplicationAR(data, ustid);
            if (sreturnData11 == null) {
                System.out.println(strBuild.toString());
                int endpos = strBuild.length();
                strBuild.replace(endpos - 2, endpos, "},");
                System.out.println(strBuild.toString());
                return strBuild;
            }
            strBuild.append("children:[ ");
            for (int j = 0; j < sreturnData11.length; j++) {
                if (BMDouble.parseLong(sreturnData11[j][4]) > 1000L) {
                    if (j == sreturnData11.length - 1) {
                        if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "} ");
                        } else {
                            strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "} ");
                        }
                    } else if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    } else {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false, id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    }
                } else if (BMDouble.parseLong(sreturnData11[j][4]) < 1000L) {
                    if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\", checked: true,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    } else {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    }
                    strBuild = getAccessRightMenuRecursiveUstAR(ustid, sreturnData11[j][2] + sreturnData11[j][3], strBuild);
                }
            }
            strBuild.append("]},");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuild;
    }

    @PostMapping(value = "/menu/userLoginMenus/{ustid}", produces = "application/json")
    public ResponseEntity<String> getAccessRightMenuUstLogin(@PathVariable String ustid) {
        StringBuilder strBuild = new StringBuilder("");
        String[][] sreturnData = (String[][]) null;
        JSONObject finalResponse = new JSONObject();
        try {
            sreturnData = this.MenuAccessRepository.getAccessRightMenuUstLogin(ustid);
            if (sreturnData != null) {
                strBuild.append("[");
                for (int j = 0; j < sreturnData.length; j++) {
                    if (BMDouble.parseLong(sreturnData[j][4]) > 1000L) {
                        if (j == sreturnData.length - 1) {
                            if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                                strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            } else {
                                //strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "} ");
                            }
                        } else if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + " \", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        } else {
                            //strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id : " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + "}, ");
                        }
                    } else if (BMDouble.parseLong(sreturnData[j][4]) < 1000L) {
                        if (sreturnData[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData[j][0] + "\", checked: true , id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        } else {
                            //strBuild.append("{ label : \"" + sreturnData[j][0] + "\",checked: false, id: " + sreturnData[j][7] +", path: " + sreturnData[j][10] + ", value: " + sreturnData[j][7] + ", ");
                        }
                        strBuild = getAccessRightMenuRecursiveUstLogin(ustid, sreturnData[j][2] + sreturnData[j][3], strBuild);
                    }
                }
                int endpos = strBuild.length();
                strBuild.replace(endpos - 1, endpos, "]");
                strBuild.replace(endpos - 4, endpos - 3, " ");
            }
            String result=strBuild.toString();
            JSONArray jsonArray = new JSONArray(result);
            finalResponse.put("menuData", jsonArray);
        } catch (Exception x) {
            x.printStackTrace();
        }
        return ResponseEntity.ok(finalResponse.toString());
    }

    private StringBuilder getAccessRightMenuRecursiveUstLogin(String ustid, String data, StringBuilder strBuild) {
        String[][] sreturnData11 = (String[][]) null;
        try {
            data = data.replaceAll("\\s", "");

            sreturnData11 = this.MenuAccessRepository.findDistinctByLvlnoAndActiveAndMnuApplicationLogin(data, ustid);
            if (sreturnData11 == null) {
                System.out.println(strBuild.toString());
                int endpos = strBuild.length();
                strBuild.replace(endpos - 2, endpos, "},");
                System.out.println(strBuild.toString());
                return strBuild;
            }
            strBuild.append("children:[ ");
            for (int j = 0; j < sreturnData11.length; j++) {
                if (BMDouble.parseLong(sreturnData11[j][4]) > 1000L) {
                    if (j == sreturnData11.length - 1) {
                        if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                            strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "} ");
                        } else {
                            // strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "} ");
                        }
                    } else if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + " \", checked: true , id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    } else {
                        // strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false, id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + "}, ");
                    }
                } else if (BMDouble.parseLong(sreturnData11[j][4]) < 1000L) {
                    if (sreturnData11[j][9].equalsIgnoreCase("Y")) {
                        strBuild.append("{ label : \"" + sreturnData11[j][0] + "\", checked: true,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    } else {
                        //strBuild.append("{ label : \"" + sreturnData11[j][0] + "\",checked: false,id: " + sreturnData11[j][7] +", path: " + sreturnData11[j][10] + ", value: " + sreturnData11[j][7] + ", ");
                    }
                    strBuild = getAccessRightMenuRecursiveUstLogin(ustid, sreturnData11[j][2] + sreturnData11[j][3], strBuild);
                }
            }
            strBuild.append("]},");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuild;
    }




}

package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.repository.MenuAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.json.JSONArray;

@RestController
public class MenuAccessController {
    @Autowired
    private MenuAccessRepository MenuAccessRepository;


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
                    if (Long.parseLong(sreturnData[j][4]) > 1000L) {
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
                    } else if (Long.parseLong(sreturnData[j][4]) < 1000L) {
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
                if (Long.parseLong(sreturnData11[j][4]) > 1000L) {
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
                } else if (Long.parseLong(sreturnData11[j][4]) < 1000L) {
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

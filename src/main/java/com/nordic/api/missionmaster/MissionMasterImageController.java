package com.nordic.api;


import com.nordic.dto.missionmasterbean.MissionMasterImageBean;
import com.nordic.service.MissionMasterImageService;

import jdk.internal.loader.Resource;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
public class MissionMasterImageController{

    @Autowired
    private MissionMasterImageService mmis;

    //이미지 insert
    @PostMapping(value = "/image/{create_member}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void insertImage(@RequestPart MultipartFile[] uploadfiles, @PathVariable String create_member) throws Exception {
        System.out.println("controller :"+ uploadfiles);
        mmis.insertImage(uploadfiles,create_member);
    }

    //미션 번호에 따른 이미지정보 가져오기
    @GetMapping(value = "/image/{mission_no}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImgByMissionNo(@PathVariable int mission_no) throws Exception {
        return mmis.getImgByMissionNo(mission_no);
    }

    //미션 번호에 따른 이미지정보 변경
    @PutMapping(value = "/image/{update_member}/{mission_no}" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void updateImgByMissionNo(@RequestParam("uploadfiles") MultipartFile[] uploadfiles, @PathVariable String
            update_member, @PathVariable int mission_no) throws IOException, ParseException {
        mmis.updateImgByMissionNo(uploadfiles, update_member, mission_no);
    }

    @DeleteMapping("/image/{mission_no}")
    public void deleteImgByMissionNo(@PathVariable int mission_no) {
        mmis.deleteImgByMissionNo(mission_no);
    }

    // 사용 안할 예정--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/imageA")
    public List<MissionMasterImageBean> getImgAll() {
        return mmis.getImgAll();
    }


}
package com.study.web.controller;

import com.study.web.model.MultiFileDTO;
import com.study.web.service.MultiFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MultiFileController {

    @Autowired
    private MultiFileService multiFileService;

    /**
     * 파일 업로드
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/upload")
    public MultiFileDTO upload(@RequestParam("file") MultipartFile file) {

        MultiFileDTO multiFileDTO = null;

        try {
            multiFileDTO = multiFileService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return multiFileDTO;
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @return
     */
    @ResponseBody
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileId") String fileId) {

        ResponseEntity<Resource> rtn = null;
        try {
            rtn = multiFileService.download(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rtn;
    }
}

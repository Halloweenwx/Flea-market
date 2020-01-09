package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.PictureDao;
import com.tet.fleamarket.dao.PictureOss;
import com.tet.fleamarket.entity.Picture;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.tet.fleamarket.util.status.FetchStatus.BAD_REQUEST;
import static com.tet.fleamarket.util.status.FetchStatus.FETCH_SUCCESS;

/**
 * @author Hou Weiying
 * @date 2019-12-03 11:26
 */
@RestController
public class PictureController {

    @Autowired
    PictureDao pictureDao;

    @Autowired
    PictureOss pictureOss;

    @GetMapping("/picture/fetch/{pid}")
    public Result fetch(@PathVariable String pid) {
        Status status;
        Picture pictureFetched = new Picture();
        try {
            pictureFetched = pictureOss.findByPid(pid);
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, pictureFetched);
    }

    @PostMapping("/picture/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        Status status = BAD_REQUEST;
        Picture pictureSaved = new Picture();
        try {
            Picture pictureToUpload = new Picture();
            pictureToUpload.setFilename(file.getOriginalFilename());
            pictureSaved = pictureDao.save(pictureToUpload);
            pictureSaved.setFileBin(file.getBytes());
            pictureOss.save(pictureSaved);
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(status, pictureSaved);
    }
}

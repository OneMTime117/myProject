package com.yh.common.util;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yh.common.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class FileDownloadUtil {

    public static byte[] handleExcelWriter(ExcelWriter writer){
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            writer.flush(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IORuntimeException e) {
            e.printStackTrace();
        } finally {
            writer.close();
            IoUtil.close(byteArrayOutputStream);
        }
        return bytes;
    }

    public static  ResponseEntity<byte[]>  downloadFile(String downloadFileName,byte[] bytes)  {
        try {
            downloadFileName = new String(downloadFileName.getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException("文件名解析失败");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
    }
}

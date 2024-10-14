package top.shy.springboot.configure.service;

import cn.hutool.extra.qrcode.QrCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class QrCodeService {
    @Value("${custom.qrcode.content}")
    private String qrCodeContent;

    public void generateQrCode() {
        QrCodeUtil.generate(qrCodeContent,300,300,new File("qrcode.png"));
        log.info("二维码生成成功!");
    }
}

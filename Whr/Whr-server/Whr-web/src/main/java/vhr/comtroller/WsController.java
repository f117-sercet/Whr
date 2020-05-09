package vhr.comtroller;

import org.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 */
@RestController

public  class WsController {
@GetMapping("login")
    public RespBean login(){
    return RespBean.error("尚未登录，请登录");
}
@GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response)throws IOException {
     VerificationCode code=new VerificationCode();
    BufferedImage image=code.getImag();
    String text = code.getText();
    HttpSession session = request.getSession(true);
    session.setAttribute("verify_code", text);
    VerificationCode.output(image,response.getOutputStream());
}
}






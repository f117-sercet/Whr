package vhr.comtroller;

import org.model.Hr;
import org.model.RespBean;
import org.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController

public class HrInfoController {
@Autowired
    HrService hrService;
 @Value("${fastdfs.nginx.host}")
    String nginxHost;
 @GetMapping("/hr/info")
    public RespBean updateHr(@RequestBody Hr hr, Authentication authentication){
     if (hrService.updateHr(hr)==1){
         SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr,authentication.getCredentials(),authentication.getAuthorities()));
       return RespBean.ok("更新成功");
     }else
         return RespBean.error("更新失败！，请重新再试");
 }
 @GetMapping("/hr/info")
    public Hr getCurrentHr(Authentication authentication){
     return (Hr) authentication.getPrincipal();
 }
    @PutMapping("/hr/PASS")
    public RespBean updateHrPasswd(@RequestBody Map<String,Object> info){
     String oldpass= (String) info.get("oldpass");
     String pass = (String) info.get("pass");
     Integer hrid = (Integer) info.get("hrid");
     if (hrService.updateHrPasswd(oldpass,pass,hrid)){
         return RespBean.ok("更新成功");
     }
        return RespBean.error("更新失败，请重新再试！");
    }
  @PostMapping("/hr/user/userface")
    public RespBean updateHrUserface(MultipartFile file,Integer id,Authentication authentication){
      String fileId = FastDFSUtils.upload(file);
      String url = nginxHost + fileId;
      if (hrService.updateUserface(url, id) == 1) {
          Hr hr = (Hr) authentication.getPrincipal();
          hr.setUserface(url);
          SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
          return RespBean.ok("更新成功!", url);
      }
      return RespBean.error("更新失败!");
  }

}



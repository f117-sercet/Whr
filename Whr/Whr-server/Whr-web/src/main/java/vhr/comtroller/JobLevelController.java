package vhr.comtroller;

import org.model.JobLevel;
import org.model.RespBean;
import org.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;
   @GetMapping("/")
    public List<JobLevel>getAllJoblevels() {
       return jobLevelService.getAllJobLevels();
   }
   @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
       if (jobLevelService.addJobLevel(jobLevel)==1){
           return RespBean.ok("添加成功!");
       }
       return RespBean.error("添加失败！，请重新再试！");
   }
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        if (jobLevelService.deleteJobLevelById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids) {
        if (jobLevelService.deleteJobLevelsByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}

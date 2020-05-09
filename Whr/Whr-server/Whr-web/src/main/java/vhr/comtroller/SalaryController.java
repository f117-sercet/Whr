package vhr.comtroller;

import org.model.RespBean;
import org.model.Salary;
import org.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @GetMapping("/")
    public List<Salary>getAllSalaries(){
        return salaryService.getAllSalary();
    }
@PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if (salaryService.addSalary(salary)==1){
            return  RespBean.ok("添加成功!");
        }
        return  RespBean.error("添加失败！");
}
 @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        if (salaryService.deleteSalaryById(id)==1){
            return RespBean.ok("删除成功！");

        }
            return RespBean.error("删除失败，请重新再试！");
    }
   @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary){
        if (salaryService.addSalary(salary)==1){
            return RespBean.ok("更新成功！");
        }
          return RespBean.error("更新失败，请重新再试！");
    }
}

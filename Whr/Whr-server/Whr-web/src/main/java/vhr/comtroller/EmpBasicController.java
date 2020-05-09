package vhr.comtroller;

import Utils.POUItils;
import org.model.*;
import org.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    JobLevelService jobLevelService;
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
     return employeeService.getEmployeeByPage(page,size, employee,beginDateScope);

    }
   @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        if (employeeService.addEmp(employee)==1){
            return RespBean.ok("添加成功");
        }
      return RespBean.error("添加失败！");
    }
@DeleteMapping("/{id}")
    public  RespBean deleteEmpByEid(@PathVariable Integer id){
        if (employeeService.deleteEmpByEid(id)==1){
            return  RespBean.ok("删除成功！");
        }
  return RespBean.error("删除失败！");
    }
   @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateEmp(employee)==1){
            return RespBean.ok("更新成功！");
        }
     return  RespBean.error("更新失败！");
    }
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }
   @GetMapping("/ioblevels")
    public List<JobLevel> getAllJoblevels(){
        return jobLevelService.getAllJobLevels();
}
   @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPosition();
   }
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return respBean;
    }
  @GetMapping("deps")
    public List<Department>getAllDepartments(){
        return departmentService.getAllDepartments();
  }
  @PostMapping("/export")
    public ResponseEntity<byte[]>exportData(){
    List<Employee>list= (List<Employee>) employeeService.getEmployeeByPage(null, null, new Employee(),null).getData();
    return POUItils.employee2Excel(list);
  }
    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POUItils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartmentsWithOutChildren(), positionService.getAllPosition(), jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");}
    }




package SpringBoot_API.Controller;

import SpringBoot_API.Pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class UserController {

    //moco接口访问地址：localhost:8011//member//login?mobilephone=admin&pwd=123456
    //@RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")注解中：value = "/login"表示接口地址，
    // produces = "application/json;charset=UTF-8"表示编码字符集，method = RequestMethod.POST表示接口访问方式
    @RequestMapping(value = "/login")
    //produces = "application/json;charset=UTF-8"
    public Result login(String mobilephone, String pwd){
        if(mobilephone == null || mobilephone.length() ==0){
            return  new Result("2013","参数错误，mobilephone不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return  new Result("2013","参数错误，pwd不能为空");
        }
        //在调用equals()方法的时候，将常量放置在前面，可以防止在变量为空时出现空指针异常
        if("18812345678".equals(mobilephone) && "a123456".equals(pwd)){
            return new Result("10001","登陆成功");
        }else{
            return new Result("20111","用户名或密码错误");
        }
    }

    @RequestMapping(value = "/loginPostJSON",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    //produces = "application/json;charset=UTF-8"
    public Result loginGetJSON(@RequestBody Map<String,String> map){
        String mobilephone = map.get("mobilephone");
        String pwd = map.get("pwd");
        if(mobilephone == null || mobilephone.length() ==0){
            return  new Result("2013","参数错误，mobilephone不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return  new Result("2013","参数错误，pwd不能为空");
        }
        //在调用equals()方法的时候，将常量放置在前面，可以防止在变量为空时出现空指针异常
        if("18812345678".equals(mobilephone) && "a123456".equals(pwd)){
            return new Result("10001","登陆成功");
        }else{
            return new Result("20111","用户名或密码错误");
        }
    }

    @RequestMapping(value = "/loginGetJSON",method = RequestMethod.GET,consumes = "application/json;charset=UTF-8")
    //produces = "application/json;charset=UTF-8"
    public Result loginPostJSON(String mobilephone, String pwd){
        if(mobilephone == null || mobilephone.length() ==0){
            return  new Result("2013","参数错误，mobilephone不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return  new Result("2013","参数错误，pwd不能为空");
        }
        //在调用equals()方法的时候，将常量放置在前面，可以防止在变量为空时出现空指针异常
        if("18812345678".equals(mobilephone) && "a123456".equals(pwd)){
            return new Result("10001","登陆成功");
        }else{
            return new Result("20111","用户名或密码错误");
        }
    }

    @RequestMapping(value = "/loginPostForm",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    //produces = "application/json;charset=UTF-8"
    public Result loginPostForm(String mobilephone, String pwd){
        if(mobilephone == null || mobilephone.length() ==0){
            return  new Result("2013","参数错误，mobilephone不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return  new Result("2013","参数错误，pwd不能为空");
        }
        //在调用equals()方法的时候，将常量放置在前面，可以防止在变量为空时出现空指针异常
        if("18812345678".equals(mobilephone) && "a123456".equals(pwd)){
            return new Result("10001","登陆成功");
        }else{
            return new Result("20111","用户名或密码错误");
        }
    }

    @RequestMapping(value = "/loginGetForm",method = RequestMethod.GET,consumes = "application/x-www-form-urlencoded")
    //produces = "application/json;charset=UTF-8"
    public Result loginGetForm(String mobilephone, String pwd){
        if(mobilephone == null || mobilephone.length() ==0){
            return  new Result("2013","参数错误，mobilephone不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return  new Result("2013","参数错误，pwd不能为空");
        }
        //在调用equals()方法的时候，将常量放置在前面，可以防止在变量为空时出现空指针异常
        if("18812345678".equals(mobilephone) && "a123456".equals(pwd)){
            return new Result("10001","登陆成功");
        }else{
            return new Result("20111","用户名或密码错误");
        }
    }
}

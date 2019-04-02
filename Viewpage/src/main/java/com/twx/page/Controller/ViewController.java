package com.twx.page.Controller;

import com.twx.page.Model.Book;
import com.twx.page.Model.Danju;
import com.twx.page.Model.Guanli;
import com.twx.page.Model.User;
import com.twx.page.service.BookService;
import com.twx.page.service.DanjuService;
import com.twx.page.service.GuanliService;
import com.twx.page.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

//@RestController
@Controller
public class ViewController {

    Logger log = Logger.getLogger(this.getClass().getName());


    @Autowired
    UserService userService;

    @Autowired
    GuanliService guanliService;

    @Autowired
    BookService bookService;

    @Autowired
    DanjuService danjuService;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /*
    用户功能
    登录，注册，查看书籍，借书还书
    */
    //获取注册前台页面
    @GetMapping("/regist")
    public ModelAndView registForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("regist/regist");
        mv.addObject("user", new User());
        return mv;
    }

    //from表单提交并远程调用保存服务
    @PostMapping("/regist")
    public String registSubmit(User user,HttpServletRequest request) {
        String msg = userService.savaUser(user);
        if (msg.equals("fail")) {
            return "commpage/regist-fail";
        } else {
            String usermsg = userService.findByUnameAndUpass(user.getUname(), user.getUpass());
            request.getSession().setAttribute("userInfo", usermsg);
            return "redirect:view/booklist";
        }
    }

    //获取登录页面
    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        mv.addObject("user", new User());
        return mv;
    }

    //登录提交表单调用远程服务
    @PostMapping("/login")
    public String LoginSubmit(User user, HttpServletRequest request) {
        String msg = userService.findByUnameAndUpass(user.getUname(), user.getUpass());
        log.info("——————user服务的值—————" + msg);
        if (msg.equals("fail")) {
            return "commpage/login-close";
        } else {
            log.info("====loginsessionId====" + request.getSession().getId());
            request.getSession().setAttribute("userInfo", msg);
            log.info("====login-userInof====" + request.getSession().getAttribute("userInfo"));
            return "redirect:view/booklist";
        }
    }

    //用户分页查看书籍
    @RequestMapping("/booklist")
    public ModelAndView userbooklist(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) throws Exception {
        log.info("————————pagenum值:" + pageNum);
        List<Book> booklist = bookService.findpage(pageNum);
        modelMap.addAttribute("booklist", booklist);
        return new ModelAndView("userpage/index");
    }

    //查找书籍
    @RequestMapping("/findbook/{bid}")
    public ModelAndView findbook(ModelMap modelMap, @PathVariable Long bid) {
        Book book = bookService.findbook(bid);
        modelMap.addAttribute("book", book);
        log.info("————书籍详细时间格式————" + book.getBtime());
        return new ModelAndView("userpage/bookshow");
    }

    //借书
    @RequestMapping("/jiebook/{bid}")
    @ResponseBody
    public String jiebook(@PathVariable Long bid, HttpServletRequest request) {
        Long uid = Long.parseLong(request.getSession().getAttribute("userInfo").toString());
        Date nowtime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowtime);
        cal.add(Calendar.MONTH, 1);
        log.info("====jiebook-uid====" + uid);
        Danju danju = new Danju();
        danju.setBid(bid);
        danju.setUid(uid);
        danju.setJietime(nowtime);
        danju.setHuantime(cal.getTime());
        log.info("===viewjie===" + bid + "--uid--" + uid + nowtime + cal.getTime());
        log.info("==view-danju==" + danju.getBid() + "--uid--" + danju.getUid() + danju.getJietime() + danju.getHuantime());
        String danjumsg = danjuService.Jiebook(danju);
        log.info("-------danju t ro f-------" + danjumsg);
        if (danjumsg.equals("success")) {
            String jianmsg = bookService.jiannum(bid);
            log.info("-------jian t ro f-------" + jianmsg);
            if (jianmsg.equals("fail")) {
                return "2";
            } else {
                return "1";
            }
        } else {
            return "2";
        }
    }

    //查看已借书籍
    @RequestMapping("/jiebooklist")
    public ModelAndView jiebooklist(ModelMap modelMap, HttpServletRequest request) {
        Long uid = Long.parseLong(request.getSession().getAttribute("userInfo").toString());
        log.info("===pagejie===" + uid);
        List<Danju> danjuList = danjuService.jiebooklist(uid);
        Book book = null;
        List<Book> bookuserlist = new ArrayList<>();
        for (int i = 0; i < danjuList.size(); i++) {
            JSONObject jsonObject = JSONObject.fromObject(danjuList.get(i));
            Danju danju = (Danju) JSONObject.toBean(jsonObject, Danju.class);
            Long bid = danju.getBid();
            log.info("====pagejie-bid====" + bid);
            book = bookService.findbook(bid);
            bookuserlist.add(book);
        }
        log.info("====pagejie-blist====" + bookuserlist.size());
        modelMap.addAttribute("booklist", bookuserlist);
        modelMap.addAttribute("danjulist", danjuList);
        return new ModelAndView("userpage/jiebooklist");
    }

    //还书
    @ResponseBody
    @RequestMapping("/guihuan/{bid}")
    public String Guihuan(@PathVariable Long bid, HttpServletRequest request) {
        Long uid = Long.parseLong(request.getSession().getAttribute("userInfo").toString());
        String danjumsg = danjuService.Guihuan(uid, bid);
        if (danjumsg.equals("success")) {
            bookService.addnum(bid);
            return "1";
        } else {
            return "2";
        }
    }

    //续借
    @ResponseBody
    @RequestMapping("/xujie/{bid}")
    public String Xujie(@PathVariable Long bid, HttpServletRequest request) {
        Long uid = Long.parseLong(request.getSession().getAttribute("userInfo").toString());
        String msg = danjuService.Xujie(uid, bid);
        if (msg.equals("success")) {
            return "1";
        } else {
            return "2";
        }
    }

    //书名模糊查询
    @RequestMapping("/likebookbyname")
    public ModelAndView likebookByname(ModelMap modelMap, String bname) {
        if (bname == "") {
            return new ModelAndView("redirect:view/booklist");
        } else {
            List<Book> bookList = bookService.likebookByname(bname);
            modelMap.addAttribute("booklist", bookList);
            return new ModelAndView("userpage/userbooklist");
        }
    }



    /*
    管理员功能
    登录，查看书籍，修改书籍信息，新增书籍，删除书籍
    修改用户信息，删除用户,用户还书，用户续借
    */


    //管理登录表单
    @GetMapping("/guanlilogin")
    public ModelAndView guanliloginForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/guanlilogin");
        mv.addObject("user", new Guanli());
        return mv;
    }

    //管理登录提交表单调用远程服务
    @PostMapping("/guanlilogin")
    public String guangliLoginSubmit(Guanli guangli, HttpServletRequest request) {
        String mag = guanliService.findByGnameAndGpass(guangli.getGname(), guangli.getGpass());
        if (mag.equals("success")) {
            request.getSession().setAttribute("userInfo", guangli.getGname() + " - " + guangli.getGpass());
            return "redirect:view/guanlibooklist";
        } else
            return "commpage/login-close";
    }

    //管理分页查看书籍
    @RequestMapping("/guanlibooklist")
    public ModelAndView guanlibooklist(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) throws Exception {
        log.info("————————pagenum值:" + pageNum);
        List<Book> booklist = bookService.findpage(pageNum);
        modelMap.addAttribute("booklist", booklist);
        return new ModelAndView("guanlipage/index");
    }

    //管理书名模糊查询
    @RequestMapping("/guanlilikebookbyname")
    public ModelAndView guanlilikebookByname(ModelMap modelMap, String bname) {
        if (bname == "") {
            return new ModelAndView("redirect:view/guanlibooklist");
        } else {
            List<Book> bookList = bookService.likebookByname(bname);
            modelMap.addAttribute("booklist", bookList);
            return new ModelAndView("guanlipage/guanlibooklist");
        }
    }

    //新增书籍
    @GetMapping("/newbook")
    public ModelAndView newbookForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("guanlipage/newbook");
        mv.addObject("book", new Book());
        return mv;
    }

    //提交表单 调用远程服务
    @PostMapping("/newbook")
    public String newbookSub(Book book) {
        log.info("==========前台信息===========" + book.getBname() + book.getBpress() + book.getBedsc() + book.getBworks() + book.getBtime());
        String msg = bookService.sava(book);
        if (msg.equals("success")) {
            return "redirect:view/guanlibooklist";
        } else {
            return "commpage/newbook-fail";
        }
    }

    //获取修改信息页面
    @GetMapping("/updatabook/{bid}")
    public ModelAndView updatabook(ModelMap modelMap, @PathVariable Long bid) {
        Book book = bookService.findbook(bid);
        modelMap.addAttribute("book", book);
        log.info("——————————书本id:" + book.getBid());
        log.info("—————book time——————" + book.getBtime());
        return new ModelAndView("guanlipage/updatabook");
    }

    //修改书籍表单提交
    @PostMapping("/updatabook")
    public String updatabook(Book book) {
        log.info("——————pagr time——————" + book.getBtime());
        log.info("——————page书本名字———————" + book.getBname());
        log.info("—————page书本id—————" + book.getBid());
        String msg = bookService.updatabook(book);
        if (msg.equals("success")) {
            return "redirect:view/guanlibooklist";
        } else {
            return "commpage/updata-fail";
        }
    }

    //删除书籍
    @GetMapping("/deletbook/{bid}")
    @ResponseBody
    public String deletebook(@PathVariable Long bid) throws Exception {
        String msg = bookService.deletebook(bid);
        if (msg.equals("success")) {
            return "1";
        } else {
            return "2";
        }
    }


    //查询页面
    @GetMapping("/queryName")
    public ModelAndView queryNamepage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("guanlipage/guanli-name");
        return mv;
    }

    //管理员通个名字查找用户所借书籍
    @RequestMapping("/guanliqueryname")
    public ModelAndView queryUser(String uname, ModelMap modelMap, Model model, HttpServletRequest request) {
        log.info("-----name------"+uname);
        //request.getSession().setAttribute("uname",uname);
        //String userMsg = userService.findByUname(request.getSession().getAttribute("uname").toString());
        String userMsg = userService.findByUname(uname);
        Long  uid= Long.parseLong(userMsg);
        List<Danju> danjuList = danjuService.jiebooklist(uid);
        Book book = null;
        List<Book> bookuserlist = new ArrayList<>();
        for (int i = 0; i < danjuList.size(); i++) {
            JSONObject jsonObject = JSONObject.fromObject(danjuList.get(i));
            Danju danju = (Danju) JSONObject.toBean(jsonObject, Danju.class);
            Long bid = danju.getBid();
            log.info("====pagejie-bid====" + bid);
            book = bookService.findbook(bid);
            bookuserlist.add(book);
        }
        log.info("====pagejie-blist====" + bookuserlist.size());
        modelMap.addAttribute("booklist", bookuserlist);
        modelMap.addAttribute("danjulist", danjuList);
        model.addAttribute("uname", uname);
        model.addAttribute("uid", uid);
        request.getSession().setAttribute("uid", uid);
        request.getSession().setAttribute("uname",uname);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("guanlipage/guanliquery");
        return mv;
    }

    //管理员还书
    @RequestMapping("/guanlihuan/{bid}")
    @ResponseBody
    public String guanliHuan(@PathVariable Long bid, HttpServletRequest request) {

        Long uid = Long.parseLong(request.getSession().getAttribute("uid").toString());
        String msg = danjuService.Guanlihuan(uid, bid);
        if (msg.equals("success")) {
            return "1";
        } else {
            return "fail";
        }
    }

    //管理员续借
    @RequestMapping("/guanlixu/{bid}")
    @ResponseBody
    public String guanliXu(@PathVariable Long bid, HttpServletRequest request) {
        Long uid = Long.parseLong(request.getSession().getAttribute("uid").toString());
        String msg = danjuService.Guanlixu(uid, bid);
        if (msg.equals("success")) {
            return "1";
        } else {
            return "2";
        }
    }

    //管理员分页查找用户
    @RequestMapping("/guanlilistuser")
    public ModelAndView userlist(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) throws Exception {
        log.info("————————pagenum值:" + pageNum);
        List<User> userlist = userService.findpage(pageNum);
        modelMap.addAttribute("userlist", userlist);
        return new ModelAndView("guanlipage/guanlilist-user");
    }

    //管理员删除用户
    @GetMapping("/deleteuser/{uid}")
    @ResponseBody
    public String deleteuser(@PathVariable Long uid) throws Exception {
        String msg = userService.deleteuser(uid);
        if (msg.equals("success")) {
            return "1";
        } else {
            return "2";
        }
    }

    //获取修改信息页面
    @GetMapping("/updatauser/{uid}")
    public ModelAndView updatauser(ModelMap modelMap, @PathVariable Long uid) {
        User user = userService.finduser(uid);
        modelMap.addAttribute("user", user);
        return new ModelAndView("guanlipage/updatauser");
    }

    //修改用户表单提交
    @PostMapping("/updatauser")
    public String updatauser(User user) {
        String msg = userService.updatauser(user);
        if (msg.equals("success")) {
            return "redirect:view/guanlilistuser";
        } else {
            return "commpage/updata-fail";
        }
    }

    //用户名模糊查询
    @RequestMapping("/likeuserbyname")
    public ModelAndView likeuserByname(ModelMap modelMap, String uname) {
        List<User> userList = userService.likeuserByname(uname);
        modelMap.addAttribute("userlist", userList);
        return new ModelAndView("guanlipage/guanlilist-user");
    }


    //退出系统
    @RequestMapping("/exit")
    public String Exit(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:view/login";
    }



}

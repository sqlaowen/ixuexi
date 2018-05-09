using com.admin.business;
using com.admin.common;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace com.admin.web.Controllers
{
    public class HomeController : Controller
    {
        //
        // GET: /Home/

        public ActionResult Index()
        {

            return View();
        }

        public ActionResult Login()
        {
            return View();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="account">账户</param>
        /// <param name="pwd">密码</param>
        /// <param name="rmb">记住我</param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Login(string account, string pwd, bool rmb = false)
        {
            B_SysUser bll = new B_SysUser();
            var user = bll.GetByAccount(account);
            if (user == null)
            {
                ViewBag.ErrorMsg = "用户不存在！";
                return View();
            }
            if (user.State == 0)
            {
                ViewBag.ErrorMsg = "用户已被禁用！";
                return View();
            }
            if (user.Pwd != pwd)
            {
                ViewBag.ErrorMsg = "用户密码不正确！";
                return View();
            }
            if (rmb)
            {
                //RequestHelper.SetCookie("USER_NAME", user.Account);
                //FormsAuthenticationTicket Ticket = new FormsAuthenticationTicket(1, user.Account, DateTime.Now, DateTime.Now.AddMinutes(30), false, "");
                ////加密票据
                //string hashTicket = FormsAuthentication.Encrypt(Ticket);
                //HttpCookie userCookie = new HttpCookie(FormsAuthentication.FormsCookieName, hashTicket);
                //Response.Cookies.Add(userCookie);
            }
            Session["CurrentUser"] = user;
            return RedirectToAction("Index");
        }

        public ActionResult Index2()
        {

            return View();
        }
    }
}

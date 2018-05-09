using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using com.admin.business;
using com.admin.poco;

namespace com.admin.web.Controllers
{
    //SysUser
    public class UserController : Controller
    {
        //
        // GET: /Menu/

        public ActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// 获取数据
        /// </summary>
        /// <returns></returns>
        [HttpPost]
        public ActionResult GetList()
        {
            B_SysUser bll = new B_SysUser();
            var page = bll.Page(Request.Form);

            return Json(new { list = page.DataList, count = page.RowNum });
        }

        /// <summary>
        /// 添加/修改页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Add(int id = 0)
        {
            SysUser entity = null;
            if (id == 0)
            {
                entity = new SysUser();
            }
            else
            {
                B_SysUser bll = new B_SysUser();
                entity = bll.GetSingle(id);
            }
            return View(entity);
        }

        /// <summary>
        /// 保存
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Save(SysUser entity)
        {
            B_SysUser bll = new B_SysUser();
            var user = bll.GetByAccount(entity.Account, entity.UserId);
            if(user!=null)
                return Json(new { error = "1", msg = string.Format("账号【{0}】已经存在", entity.Account) });
            try
            {
                if (entity.UserId == 0)
                {
                    entity.CreateTime = DateTime.Now;
                    //entity.CreateUser = 1;
                    entity.UpdateTime = DateTime.Now;
                    //entity.UpdateUser = 1;
                    bll.Insert(entity);
                }
                else
                {
                    entity.UpdateTime = DateTime.Now;
                    //entity.UpdateUser = 1;
                    bll.Update(entity);
                }
                return Json(new { error = "0" });
            }
            catch
            {
                return View(entity);
            }
        }

        /// <summary>
        /// 单个删除
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Del(SysUser entity)
        {
            entity.State = 0;
            B_SysUser bll = new B_SysUser();
            if (bll.Update(entity) > 0)
                return Json(new { error = "0" });
            else
                return Json(new { error = "1" });
        }

        /// <summary>
        /// 批量删除
        /// </summary>
        /// <returns></returns>
        [HttpPost]
        public ActionResult DelBatch()
        {
            string ids = Request.Form["ids"];
            B_SysUser bll = new B_SysUser();
            if (bll.DelBatch(ids) > 0)
                return Json(new { error = "0" });
            else
                return Json(new { error = "1" });
        }

        /// <summary>
        /// 账户是不是已经存在
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult AccountExists(string id)
        {
            B_SysUser bll = new B_SysUser();
            var user = bll.GetByAccount(id);
            if (user != null)
                return Json(new { exists = 1, err = string.Format("账号【{0}】已经存在",id) });
            return Json(new { exists = 0, err = "" });
        }
    }
}
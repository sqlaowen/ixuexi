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
	//SysRole
	public class RoleController : Controller
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
            B_SysRole bll = new B_SysRole();
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
            SysRole entity = null;
            if (id == 0)
			{
                entity = new SysRole();
			}
            else
			{
				B_SysRole bll = new B_SysRole();
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
        public ActionResult Save(SysRole entity)
        {
            B_SysRole bll = new B_SysRole();
            try
            {
                if (entity.RoleId == 0)
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
        public ActionResult Del(SysRole entity)
        {
            entity.State = 0;
            B_SysRole bll = new B_SysRole();
            if(bll.Update(entity)>0)
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
            B_SysRole bll = new B_SysRole();
            if (bll.DelBatch(ids) > 0)
                return Json(new { error = "0" });
            else
                return Json(new { error = "1" });
        }

	}
}
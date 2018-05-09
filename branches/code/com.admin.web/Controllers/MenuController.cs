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
    //SysMenu
    public class MenuController : Controller
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
            B_SysMenu bll = new B_SysMenu();
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
            B_SysMenu bll = new B_SysMenu();
            SysMenu entity = null;
            if (id == 0)
            {
                entity = new SysMenu();
            }
            else
            {
                entity = bll.GetSingle(id);
            }
            ViewBag.ChildMenu = bll.GetChildMenu(0);
            return View(entity);
        }

        /// <summary>
        /// 保存
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Save(SysMenu entity)
        {
            B_SysMenu bll = new B_SysMenu();
            try
            {
                if (entity.MenuId == 0)
                {
                    entity.CreateTime = DateTime.Now;
                    entity.CreateUser = 1;
                    entity.UpdateTime = DateTime.Now;
                    entity.UpdateUser = 1;
                    if (entity.PId == null)
                        entity.Level = 1;
                    if (entity.Level != 3)
                        entity.Url = null;
                    bll.Insert(entity);
                }
                else
                {
                    if (entity.Level != 3)
                        entity.Url = null;
                    entity.UpdateTime = DateTime.Now;
                    entity.UpdateUser = 1;
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
        public ActionResult Del(SysMenu entity)
        {
            entity.State = 0;
            B_SysMenu bll = new B_SysMenu();
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
            B_SysMenu bll = new B_SysMenu();
            if (bll.DelBatch(ids) > 0)
                return Json(new { error = "0" });
            else
                return Json(new { error = "1" });
        }

        /// <summary>
        /// 查找子级菜单
        /// </summary>
        /// <param name="id">id为0时查找第一级</param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult GetChildMenu(int id)
        {
            B_SysMenu bll = new B_SysMenu();
            return Json(bll.GetChildMenu(id));
        }

        /// <summary>
        /// 根据Id查询所有上级
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult GetParentMenuById(int id = 0)
        {
            if (id == 0)
                return Json("");
            B_SysMenu bll = new B_SysMenu();
            return Json(bll.GetParentMenuById(id));
        }

        /// <summary>
        /// 查找左侧菜单树
        /// </summary>
        /// <returns></returns>
        public ActionResult GetMenu()
        {
            IList<TreeNode> nodeList = new List<TreeNode>();
            B_SysMenu bll = new B_SysMenu();
            IList<SysMenu> list = bll.GetSideBarMenu();
            var nav = list.Where(x => x.PId == null);
            foreach (SysMenu menu in nav)
            {
                nodeList.Add(TreeNode.GetInstance(menu));
            }

            return Json(nodeList);
        }

        #region 菜单
        private class TreeNode3
        {
            /// <summary>
            /// 页面id,用于避免重复打开页面
            /// </summary>
            public string id { get; set; }

            /// <summary>
            /// 标题
            /// </summary>
            public string text { get; set; }

            /// <summary>
            /// 页面URL
            /// </summary>
            public string href { get; set; }
        }

        private class TreeNode2
        {
            public TreeNode2()
            {
                items = new List<TreeNode3>();
            }

            /// <summary>
            /// 标题
            /// </summary>
            public string text { get; private set; }

            /// <summary>
            /// 三级菜单集合
            /// </summary>
            public IList<TreeNode3> items { get; private set; }

            public static TreeNode2 GetInstance(SysMenu menu)
            {
                TreeNode2 instance = new TreeNode2();
                instance.text = menu.Name;
                if (menu.Children == null)
                    return instance;
                foreach (SysMenu child in menu.Children)
                {
                    TreeNode3 subNode = new TreeNode3()
                    {
                        id = child.MenuId.ToString(),
                        text = child.Name,
                        href = child.Url
                    };
                    instance.items.Add(subNode);
                }
                return instance;
            }
        }

        private class TreeNode
        {
            /// <summary>
            /// 模块名称
            /// </summary>
            public string text { get; private set; }

            /// <summary>
            /// 模块的编号，用于定位模块
            /// </summary>
            public string id { get; private set; }

            /// <summary>
            /// 左侧菜单是否默认收缩,默认false
            /// </summary>
            public bool collapsed { get; private set; }

            /// <summary>
            /// 模块默认显示的主页，使用页面的id
            /// </summary>
            public string homePage { get; private set; }

            /// <summary>
            /// 菜单的集合，是一个数组，其中每个对象代表一个二级菜单
            /// </summary>
            public IList<TreeNode2> menu { get; private set; }

            private TreeNode()
            {
                this.collapsed = false;
                this.homePage = "";
                this.menu = new List<TreeNode2>();
            }

            public static TreeNode GetInstance(SysMenu menu)
            {
                TreeNode instance = new TreeNode();
                instance.id = menu.MenuId.ToString();
                instance.text = menu.Name;
                if (menu.Children == null)
                    return instance;

                foreach (SysMenu child in menu.Children)
                {
                    TreeNode2 subNode = TreeNode2.GetInstance(child);
                    instance.menu.Add(subNode);
                }
                return instance;
            }
        }

        #endregion
    }
}
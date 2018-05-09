using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using Dapper;
using System.Data;
using com.admin.poco;
using com.admin.common;

namespace com.admin.business
{
    //SysMenu
    public class B_SysMenu
    {
        Db_base db = new Db_base();
        /// <summary>
        /// 查询单个对象
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SysMenu GetSingle(object id)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Get<SysMenu>(id);
            }
        }

        /// <summary>
        /// 添加对象，返回主键
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public int? Insert(SysMenu entity)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Insert(entity);
            }
        }


        /// <summary>
        /// 修改对象，返回受影响的行数
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public int Update(SysMenu entity)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Update(entity);
            }
        }

        /// <summary>
        /// 删除对象，返回受影响的行数
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public int Delete(SysMenu entity)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Delete(entity);
            }
        }

        /// <summary>
        /// 批量删除(逻辑删除)
        /// </summary>
        /// <param name="ids"></param>
        /// <returns></returns>
        public int DelBatch(string ids)
        {
            string sql = "UPDATE SysMenu SET [State]=0 WHERE MenuId IN @ids";
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Execute(sql, new { ids = ConvertHelper.Str2IntArr(ids) });
            }
        }


        /// <summary>
        /// 分页
        /// </summary>
        /// <param name="pageindex"></param>
        /// <param name="pagesize"></param>
        public Paging<SysMenu> Page(NameValueCollection nv)
        {
            string strWhere = "";
            strWhere += " and m.[State]=1";

            Paging<SysMenu> page = new Paging<SysMenu>();
            page.Table = "SysMenu m";
            page.Field = "*";
            page.Sort = "m.MenuId asc";
            page.PageIndex = int.Parse(nv["pageIndex"] ?? "1");
            page.PageSize = int.Parse(nv["pageSize"] ?? "20");
            page.Where = strWhere;

            ///////////////////////////////////////
            var _dy = new DynamicParameters();
            _dy.Add("table", page.Table);
            _dy.Add("field", page.Field);
            _dy.Add("where", page.Where);
            _dy.Add("sort", page.Sort);
            _dy.Add("pindex", page.PageIndex);
            _dy.Add("psize", page.PageSize);
            _dy.Add("rownum", dbType: DbType.Int32, direction: ParameterDirection.Output);
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                page.DataList = _conn.Query<SysMenu>("pro_paging", _dy, commandType: CommandType.StoredProcedure);
            }
            page.RowNum = _dy.Get<int>("rownum");
            return page;
        }

        /// <summary>
        /// 查找子级菜单
        /// </summary>
        /// <param name="pid">pid为0时查找第一级</param>
        /// <returns></returns>
        public IList<SysMenu> GetChildMenu(int pid)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                if (pid == 0)
                {
                    string sql = "SELECT * FROM dbo.SysMenu WHERE 1=1 AND PId IS NULL AND [State]=1";
                    return _conn.Query<SysMenu>(sql).ToList();
                }
                return _conn.GetList<SysMenu>(new { PId = pid, State = 1 }).ToList();
            }
        }

        /// <summary>
        /// 根据Id查询所有上级
        /// </summary>
        /// <returns></returns>
        public IList<SysMenu> GetParentMenuById(int id)
        {
            string sql = @"
;WITH cte1 AS (
SELECT * FROM SysMenu WHERE [State]=1 AND MenuId=@id
UNION ALL
SELECT a.* FROM SysMenu a
JOIN cte1 c ON a.MenuId=c.Pid
WHERE a.[State]=1
)
SELECT * FROM cte1 where MenuId!=@id ORDER BY Level
";
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Query<SysMenu>(sql, new { id = id }).ToList();
            }
        }

        /// <summary>
        /// 查找左侧菜单树
        /// </summary>
        /// <returns></returns>
        public IList<SysMenu> GetSideBarMenu()
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                var list = _conn.GetList<SysMenu>(new { state = 1 }).ToList<SysMenu>();
                Plug(list);
                return list;
            }
        }

        /// <summary>
        /// 上下级菜单互塞
        /// </summary>
        /// <param name="mlist"></param>
        private void Plug(IList<SysMenu> mlist)
        {
            foreach (SysMenu p in mlist)
            {
                foreach (SysMenu c in mlist)
                {
                    if (c.PId != p.MenuId)
                        continue;
                    c.Parent = p;
                    if (p.Children == null)
                    {
                        p.Children = new List<SysMenu>();
                    }
                    if (!p.Children.Contains(c))
                    {
                        p.Children.Add(c);
                    }
                }
            }
        }

    }
}
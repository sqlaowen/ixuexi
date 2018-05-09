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
    //SysUser
    public class B_SysUser
    {
        Db_base db = new Db_base();
        /// <summary>
        /// 查询单个对象
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SysUser GetSingle(object id)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Get<SysUser>(id);
            }
        }

        /// <summary>
        /// 添加对象，返回主键
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public int? Insert(SysUser entity)
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
        public int Update(SysUser entity)
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
        public int Delete(SysUser entity)
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
            string sql = "UPDATE SysUser SET [State]=0 WHERE UserId IN @ids";
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
        public Paging<SysUser> Page(NameValueCollection nv)
        {
            string strWhere = "";
            if (!string.IsNullOrEmpty(nv["State"]))
                strWhere+=string.Format(" and [State]={0}",nv["State"]);
            if (!string.IsNullOrEmpty(nv["Name"]))
                strWhere += string.Format(" and Name like '%{0}%'", nv["Name"]);
            Paging<SysUser> page = new Paging<SysUser>();
            page.Table = "SysUser";
            page.Field = "*";
            page.Sort = "UserId asc";
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
                page.DataList = _conn.Query<SysUser>("pro_paging", _dy, commandType: CommandType.StoredProcedure);
            }
            page.RowNum = _dy.Get<int>("rownum");
            return page;
        }

        /// <summary>
        /// 通过账号查找用户
        /// </summary>
        /// <param name="account"></param>
        /// <param name="id">排除自己ID</param>
        /// <returns></returns>
        public SysUser GetByAccount(string account, int id = 0)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                IList<SysUser> list = null;
                if (id == 0)
                {
                    list = _conn.GetList<SysUser>(new { Account = account }).ToList();
                }
                else
                {
                    string sql = "SELECT * FROM dbo.SysUser WHERE Account=@account AND UserId <>@userid";
                    list = _conn.Query<SysUser>(sql, new { account = account, userid = id }).ToList();
                }
                if (list.Count > 0)
                    return list[0];
                return null;
            }
        }
    }
}
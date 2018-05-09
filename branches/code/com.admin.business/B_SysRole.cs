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
	//SysRole
	public class B_SysRole
	{
		Db_base db = new Db_base();
        /// <summary>
        /// 查询单个对象
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SysRole GetSingle(object id)
        {
            using (var _conn = db.CreateInstance())
            {
                _conn.Open();
                return _conn.Get<SysRole>(id);
            }
        }
		
		/// <summary>
        /// 添加对象，返回主键
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public int? Insert(SysRole entity)
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
        public int Update(SysRole entity)
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
        public int Delete(SysRole entity)
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
            string sql = "UPDATE SysRole SET [State]=0 WHERE RoleId IN @ids";
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
        public Paging<SysRole> Page(NameValueCollection nv)
        {
            string strWhere = "";
            strWhere += " and [state]=1";
			
            Paging<SysRole> page = new Paging<SysRole>();
            page.Table = "SysRole";
            page.Field = "*";
            page.Sort = "RoleId asc";
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
                page.DataList = _conn.Query<SysRole>("pro_paging", _dy, commandType: CommandType.StoredProcedure);
            }
            page.RowNum = _dy.Get<int>("rownum");
            return page;
        }
	}
}
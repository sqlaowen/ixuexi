using System.Collections.Generic;

namespace com.admin.common
{
    /// <summary>
    /// 通用分页模型
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class Paging<T> where T : class,new()
    {
        public string Table { get; set; }
        public string Field { get; set; }
        public string Where { get; set; }
        public string Sort { get; set; }
        public int PageIndex { get; set; }
        private int _pagesize = 20;
        public int PageSize
        {
            get { return _pagesize; }
            set { _pagesize = value; }
        }

        /// <summary>
        /// 总数据数量
        /// </summary>
        public int RowNum { get; set; }
        
        /// <summary>
        /// 数据列表
        /// </summary>
        public IEnumerable<T> DataList { get; set; }
    }
}

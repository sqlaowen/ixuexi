using System;

namespace com.admin.common
{
    public class ConvertHelper
    {
        /// <summary>
        /// 将对象变量转成字符串变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>字符串变量</returns>
        public static string Obj2String(object obj)
        {
            return (obj == null) ? string.Empty : obj.ToString();
        }
        
        /// <summary>
        /// 将对象变量转成32位整数型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>32位整数型变量</returns>
        public static int Obj2Int32(object obj)
        {
            int result;
            int.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成64位整数型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>64位整数型变量</returns>
        public static long Obj2Int64(object obj)
        {
            long result;
            long.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成双精度浮点型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>双精度浮点型变量</returns>
        public static double Obj2Double(object obj)
        {
            double result;
            double.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成十进制数字变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>十进制数字变量</returns>
        public static decimal Obj2Decimal(object obj)
        {
            decimal result;
            decimal.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成布尔型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>布尔型变量</returns>
        public static bool Obj2Boolean(object obj)
        {
            bool result;
            bool.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成日期型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>日期型变量</returns>
        public static DateTime Obj2DateTime(object obj)
        {
            DateTime result;
            DateTime.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象变量转成可为null的日期型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>可为null的日期型变量</returns>
        public static DateTime? Obj2NullableDateTime(object obj)
        {
            DateTime result;
            DateTime.TryParse(Obj2String(obj), out result);
            if (result == DateTime.MinValue)
            {
                return null;
            }
            else
            {
                return result;
            }
        }
        
        /// <summary>
        /// 将字符串转成短整型变量的方法
        /// </summary>
        /// <param name="obj"></param>
        /// <returns>短整型变量</returns>
        public static short Obj2Int16(object obj)
        {
            short result;
            short.TryParse(Obj2String(obj), out result);
            return result;
        }
        
        /// <summary>
        /// 将对象转换为Byte数据类型(8位无符号整数)
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static Byte Obj2Byte(object obj)
        {
            Byte result;
            Byte.TryParse(Obj2String(obj), out result);
            return result;
        }

        /// <summary>
        /// 将对象转换为SByte数据类型(8位有符号整数)
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static SByte Obj2SByte(object obj)
        {
            SByte result;
            SByte.TryParse(Obj2String(obj), out result);
            return result;
        }
		
        /// <summary>
        /// 将对象转换为Single数据类型(单精度浮点数)
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static Single Obj2Single(object obj)
        {
            Single result;
            Single.TryParse(Obj2String(obj), out result);
            return result;
        }

        /// <summary>
        /// 将对象变量转成单精度浮点型变量的方法
        /// </summary>
        /// <param name="obj">对象变量</param>
        /// <returns>单精度浮点型变量</returns>
        public static float Obj2Float(object obj)
        {
            float result;
            float.TryParse(Obj2String(obj), out result);
            return result;
        }
		
        /// <summary>
        /// string数组转int数组
        /// </summary>
        /// <param name="strArr"></param>
        /// <returns></returns>
        public static int[] StringArr2IntArr(string[] strArr)
        {
            return Array.ConvertAll<string, int>(strArr, x => Obj2Int32(x));
        }

        /// <summary>
        /// string数组转long数组
        /// </summary>
        /// <param name="strArr"></param>
        /// <returns></returns>
        public static long[] StringArr2LongArr(string[] strArr)
        {
            return Array.ConvertAll<string, long>(strArr, x => Obj2Int64(x));
        }

        /// <summary>
        /// int数组转string数组
        /// </summary>
        /// <param name="intArr"></param>
        /// <returns></returns>
        public static string[] IntArr2StringArr(int[] intArr)
        {
            return Array.ConvertAll<int, string>(intArr, x => Obj2String(x));
        }

        /// <summary>
        /// long数组转string数组
        /// </summary>
        /// <param name="intArr"></param>
        /// <returns></returns>
        public static string[] LongArr2StringArr(long[] intArr)
        {
            return Array.ConvertAll<long, string>(intArr, x => Obj2String(x));
        }

        /// <summary>
        /// 分割数字字符串转int[]
        /// </summary>
        /// <param name="str"></param>
        /// <param name="split"></param>
        /// <returns></returns>
        public static int[] Str2IntArr(string str, string split = ",")
        {
            string[] strArr = str.Split(split.ToCharArray(), StringSplitOptions.RemoveEmptyEntries);
            return StringArr2IntArr(strArr);
        }

        /// <summary>
        /// 分割数字字符串转long[]
        /// </summary>
        /// <param name="str"></param>
        /// <param name="split"></param>
        /// <returns></returns>
        public static long[] Str2LongArr(string str, string split = ",")
        {
            string[] strArr = str.Split(split.ToCharArray(), StringSplitOptions.RemoveEmptyEntries);
            return StringArr2LongArr(strArr);
        }
    }
}

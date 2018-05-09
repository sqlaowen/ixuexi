using NPOI.HSSF.UserModel;
using NPOI.SS.Formula.Eval;
using NPOI.SS.UserModel;
using NPOI.SS.Util;
using NPOI.XSSF.UserModel;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.OleDb;
using System.IO;

namespace com.admin.common
{
    public class ExcelHelper
    {
        #region 读取 Excel
        /// <summary>
        /// 读取excel
        /// </summary>
        /// <param name="excelPath">excel路径</param>
        /// <param name="sheetAt">第几个sheet</param>
        /// <param name="isFirstHeader">首行是不是列名</param>
        /// <returns></returns>
        public static DataTable ReadExcelByNpoi(string excelPath, int sheetAt = 0, bool isFirstHeader = true)
        {
            DataTable dt;
            IWorkbook book;
            using (FileStream fs = new FileStream(excelPath, FileMode.Open, FileAccess.Read))
            {
                book = WorkbookFactory.Create(fs);
            }
            if (book == null)
                return null;
            if (book.NumberOfSheets < 1)
                return null;
            ISheet sheet = book.GetSheetAt(sheetAt);
            try
            {
                dt = ReadExcel(sheet, isFirstHeader);
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return dt;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="sheetName"></param>
        /// <param name="path"></param>
        /// <param name="hdr">YES:第一行是标题，不作内容
        /// NO:没有标题，第一行作为内容</param>
        /// <param name="imex">读写方式
        /// 0：写
        /// 1：读
        /// 2：读写
        /// </param>
        /// <returns></returns>
        public static DataTable ReadExcelByOleDb(string sheetName, string path, string hdr = "YES", int imex = 1)
        {
            using (OleDbConnection conn = new OleDbConnection())
            {
                string fileExtension = Path.GetExtension(path).ToLower();
                if (fileExtension == ".xls")
                    conn.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + path + ";" + "Extended Properties='Excel 8.0;HDR=" + hdr.Trim().ToUpper() + ";IMEX=" + imex + "'";
                else if (fileExtension == ".xlsx")
                    conn.ConnectionString = "Provider=Microsoft.ACE.OLEDB.12.0;Data Source=" + path + ";" + "Extended Properties='Excel 12.0 Xml;HDR=" + hdr.Trim().ToUpper() + ";IMEX=" + imex + "'";
                else
                    throw new Exception("非excel文件,不予执行！");

                #region //改注册表，列类型匹配该列所有行数据类形。
                ////改注册表，列类型匹配该列所有行数据类形。
                //try
                //{
                //    RegistryKey soft = Registry.LocalMachine.OpenSubKey(@"SOFTWARE\Microsoft\Jet\4.0\Engines\Excel", true);
                //    if (soft.GetValue("TypeGuessRows").ToString() != "0")
                //        soft.SetValue("TypeGuessRows", "0", RegistryValueKind.DWord);
                //    soft = Registry.LocalMachine.OpenSubKey(@"SOFTWARE\Microsoft\Office\12.0\Access Connectivity Engine\Engines\Excel", true);
                //    if (soft.GetValue("TypeGuessRows").ToString() != "0")
                //        soft.SetValue("TypeGuessRows", "0", RegistryValueKind.DWord);
                //}
                //catch(Exception ex)
                //{
                //    throw ex;
                //} 
                #endregion

                using (OleDbCommand cmd = new OleDbCommand())
                {
                    cmd.CommandText = "Select * from [" + sheetName + "$]";
                    cmd.Connection = conn;
                    using (OleDbDataAdapter adp = new OleDbDataAdapter())
                    {
                        conn.Open();
                        adp.SelectCommand = cmd;
                        DataTable dt = new DataTable();
                        adp.Fill(dt);
                        conn.Close();
                        return dt;
                    }
                }
            }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="sheet">ISheet</param>
        /// <param name="isFirstHeader">首行是不是列名</param>
        /// <returns></returns>
        internal static DataTable ReadExcel(ISheet sheet, bool isFirstHeader)
        {
            DataTable table = new DataTable();
            IRow headerRow = null;
            try
            {
                headerRow = sheet.GetRow(sheet.FirstRowNum);
                if (headerRow == null)
                    return null;
                for (int j = headerRow.FirstCellNum; j < headerRow.LastCellNum; j++)
                {
                    DataColumn column;
                    if (isFirstHeader)
                        column = new DataColumn(headerRow.GetCell(j).StringCellValue);
                    else
                        column = new DataColumn(j.ToString());
                    table.Columns.Add(column);
                }

                int i = sheet.FirstRowNum;
                if (isFirstHeader)
                    i += 1;
                for (; i <= sheet.LastRowNum; i++)
                {
                    IRow row = sheet.GetRow(i);
                    if (row == null)
                        continue;
                    DataRow dataRow = table.NewRow();
                    #region 填充dataRow
                    for (int j = row.FirstCellNum; j < headerRow.LastCellNum; j++)
                    {
                        if (row.GetCell(j) == null)
                            continue;
                        switch (row.GetCell(j).CellType)
                        {
                            case CellType.String:
                                string str = row.GetCell(j).StringCellValue;
                                if (str != null && str.Length > 0)
                                {
                                    dataRow[j] = str;
                                }
                                else
                                {
                                    dataRow[j] = null;
                                }
                                break;
                            case CellType.Numeric:
                                if (DateUtil.IsCellDateFormatted(row.GetCell(j)))
                                {
                                    dataRow[j] = DateTime.FromOADate(row.GetCell(j).NumericCellValue);
                                }
                                else
                                {
                                    dataRow[j] = Convert.ToDouble(row.GetCell(j).NumericCellValue);
                                }
                                break;
                            case CellType.Boolean:
                                dataRow[j] = Convert.ToString(row.GetCell(j).BooleanCellValue);
                                break;
                            case CellType.Error:
                                dataRow[j] = ErrorEval.GetText(row.GetCell(j).ErrorCellValue);
                                break;
                            case CellType.Formula:
                                switch (row.GetCell(j).CachedFormulaResultType)
                                {
                                    case CellType.String:
                                        string strFORMULA = row.GetCell(j).StringCellValue;
                                        if (strFORMULA != null && strFORMULA.Length > 0)
                                        {
                                            dataRow[j] = strFORMULA;
                                        }
                                        else
                                        {
                                            dataRow[j] = null;
                                        }
                                        break;
                                    case CellType.Numeric:
                                        dataRow[j] = Convert.ToString(row.GetCell(j).NumericCellValue);
                                        break;
                                    case CellType.Boolean:
                                        dataRow[j] = Convert.ToString(row.GetCell(j).BooleanCellValue);
                                        break;
                                    case CellType.Error:
                                        dataRow[j] = ErrorEval.GetText(row.GetCell(j).ErrorCellValue);
                                        break;
                                    default:
                                        dataRow[j] = "";
                                        break;
                                }
                                break;
                            default:
                                dataRow[j] = "";
                                break;
                        }
                    }
                    #endregion
                    table.Rows.Add(dataRow);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return table;
        }
        #endregion

        #region 导出 Excel

        /// <summary>
        /// 
        /// </summary>
        /// <param name="ds">数据DataSet</param>
        /// <param name="sheetNames"></param>
        /// <param name="headerCaptions">要导出列的表头数组</param>
        /// <param name="dataColNames">要导出数据的字段名数组</param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataSet ds, string[] sheetNames, IList<string[]> headerCaptions, IList<string[]> dataColNames, bool excel2003 = false)
        {
            IWorkbook excel;
            if (ds != null && ds.Tables.Count >= sheetNames.Length && sheetNames.Length == headerCaptions.Count && sheetNames.Length == dataColNames.Count)
            {
                if (!excel2003)
                    excel = new XSSFWorkbook();
                else
                    excel = new HSSFWorkbook();
                for (int i = 0; i < sheetNames.Length; i++)
                {
                    if (headerCaptions[i].Length == 0 || headerCaptions[i].Length != dataColNames[i].Length)
                    {
                        string[] defaultCols = new string[ds.Tables[i].Columns.Count];
                        for (int j = 0; j < defaultCols.Length; j++)
                        {
                            defaultCols[j] = ds.Tables[i].Columns[j].ColumnName;
                        }
                        InsertRecord(excel, ds.Tables[i], sheetNames[i], defaultCols, defaultCols, excel2003);
                    }
                    else
                    {
                        string[] headerCaption = headerCaptions[i];
                        string[] dataColName = dataColNames[i];
                        InsertRecord(excel, ds.Tables[i], sheetNames[i], headerCaption, dataColName, excel2003);
                    }
                }
            }
            else
            {
                excel = new XSSFWorkbook();
                ISheet sheet = excel.CreateSheet("Sheet1");
                sheet.CreateRow(0).CreateCell(0).SetCellValue("输入参数不正确，请检查！");
            }
            using (MemoryStream ms = new MemoryStream())
            {
                excel.Write(ms);
                return ms.ToArray();
            }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dt">数据DataTable</param>
        /// <param name="SheetName"></param>
        /// <param name="headerCaption">要导出列的表头数组</param>
        /// <param name="dataColName">要导出数据的字段名数组</param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataTable dt, string SheetName, string[] headerCaption, string[] dataColName, bool excel2003 = false)
        {
            DataSet ds = null;
            if (dt != null)
            {
                ds = new DataSet();
                ds.Tables.Add(dt);
            }
            return ExportExcel(ds, new string[] { SheetName }, new List<string[]> { headerCaption }, new List<string[]> { dataColName }, excel2003);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dt"></param>
        /// <param name="SheetName"></param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataTable dt, string SheetName, bool excel2003 = false)
        {
            string[] temp = { };
            return ExportExcel(dt, SheetName, temp, temp, excel2003);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="excel"></param>
        /// <param name="dt"></param>
        /// <param name="SheetName"></param>
        /// <param name="headTitle"></param>
        /// <param name="dataCol"></param>
        internal static void InsertRecord(IWorkbook excel, DataTable dt, string SheetName, string[] headTitle, string[] dataCol, bool excel2003)
        {
            if (dt.Rows.Count == 0)
            {
                if (string.IsNullOrEmpty(SheetName))
                    excel.CreateSheet(dt.TableName);
                else
                    excel.CreateSheet(SheetName);
                return;
            }
            //当数据行数超过65535时，则创建多个sheet
            int rows = dt.Rows.Count;

            int sheetCount = 0;
            if (excel2003)
                sheetCount = rows % 65535 == 0 ? rows / 65535 : (rows / 65535) + 1;
            else
                sheetCount = rows % 1048575 == 0 ? rows / 1048575 : (rows / 1048575) + 1;

            for (int l = 0; l < sheetCount; l++)
            {
                string sheetName = string.Empty;
                if (!string.IsNullOrEmpty(SheetName))
                {
                    sheetName = SheetName;
                }
                else if (!string.IsNullOrEmpty(dt.TableName))
                {
                    sheetName = dt.TableName;
                }

                if (sheetCount > 1)
                {
                    sheetName += (l + 1).ToString();
                }
                ISheet sheet = excel.CreateSheet(sheetName);

                #region 字段标题
                IRow rowHeader = sheet.CreateRow(0);
                for (int i = 0; i < headTitle.Length; i++)
                {
                    rowHeader.CreateCell(i).SetCellValue(headTitle[i]);
                }
                #endregion

                #region 数据行
                int startIndex = l * 65535;
                int endIndex = startIndex + 65535;
                if (endIndex > rows)
                {
                    endIndex = rows;
                }
                for (int j = startIndex; j < endIndex; j++)
                {
                    IRow row = sheet.CreateRow(j - startIndex + 1);
                    for (int k = 0; k < dataCol.Length; k++)
                    {
                        ICell cell = row.CreateCell(k);
                        System.Type rowType = dt.Rows[j][dataCol[k]].GetType();
                        string drValue = dt.Rows[j][dataCol[k]].ToString().Trim();
                        switch (rowType.ToString())
                        {
                            case "System.String"://字符串类型                           
                                cell.SetCellValue(drValue);
                                break;
                            case "System.DateTime"://日期类型
                                DateTime dateV;
                                DateTime.TryParse(drValue, out dateV);
                                cell.SetCellValue(drValue);

                                //格式化显示
                                ICellStyle cellStyle = excel.CreateCellStyle();
                                IDataFormat format = excel.CreateDataFormat();
                                cellStyle.DataFormat = format.GetFormat("yyyy-MM-dd HH:mm:ss");
                                cell.CellStyle = cellStyle;
                                break;
                            case "System.Boolean"://布尔型
                                bool boolV = false;
                                bool.TryParse(drValue, out boolV);
                                cell.SetCellValue(boolV);
                                break;
                            case "System.Int16"://整型
                            case "System.Int32":
                            case "System.Int64":
                            case "System.Byte":
                                int intV = 0;
                                int.TryParse(drValue, out intV);
                                cell.SetCellValue(intV);
                                break;
                            case "System.Decimal"://浮点型
                            case "System.Double":
                                double doubV = 0;
                                double.TryParse(drValue, out doubV);
                                cell.SetCellValue(doubV);
                                break;
                            default:
                                cell.SetCellValue("");
                                break;
                        }
                    }
                }
                #endregion
            }
        }

        #endregion

        #region 导出 Excel

        /// <summary>
        /// 
        /// </summary>
        /// <param name="ds"></param>
        /// <param name="sheetNames"></param>
        /// <param name="headerCaptions">要导出列的表头数组</param>
        /// <param name="dataColNames">要导出数据的字段名数组</param>
        /// <param name="widthCaption"></param>
        /// <param name="rList"></param>
        /// <param name="titleStyle"></param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataSet ds, string[] sheetNames, IList<string[]> headerCaptions, IList<string[]> dataColNames, string[] widthCaption, IList<CellRangeModel> rList, CellRangeModel titleStyle, bool excel2003 = false)
        {
            IWorkbook excel;
            if (ds != null && ds.Tables.Count >= sheetNames.Length && sheetNames.Length == headerCaptions.Count && sheetNames.Length == dataColNames.Count)
            {
                if (!excel2003)
                    excel = new XSSFWorkbook();
                else
                    excel = new HSSFWorkbook();
                for (int i = 0; i < sheetNames.Length; i++)
                {
                    if (headerCaptions[i].Length == 0 || headerCaptions[i].Length != dataColNames[i].Length)
                    {
                        string[] defaultCols = new string[ds.Tables[i].Columns.Count];
                        for (int j = 0; j < defaultCols.Length; j++)
                        {
                            defaultCols[j] = ds.Tables[i].Columns[j].ColumnName;
                        }
                        InsertRecord(excel, ds.Tables[i], sheetNames[i], defaultCols, defaultCols, widthCaption, rList, titleStyle, excel2003);
                    }
                    else
                    {
                        string[] headerCaption = headerCaptions[i];
                        string[] dataColName = dataColNames[i];
                        InsertRecord(excel, ds.Tables[i], sheetNames[i], headerCaption, dataColName, widthCaption, rList, titleStyle, excel2003);
                    }
                }
            }
            else
            {
                excel = new XSSFWorkbook();
                ISheet sheet = excel.CreateSheet("Sheet1");
                sheet.CreateRow(0).CreateCell(0).SetCellValue("输入参数不正确，请检查！");
            }
            using (MemoryStream ms = new MemoryStream())
            {
                excel.Write(ms);
                return ms.ToArray();
            }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="ds"></param>
        /// <param name="sheetNames"></param>
        /// <param name="rList"></param>
        /// <param name="titleStyle"></param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataSet ds, string[] sheetNames, IList<CellRangeModel> rList, CellRangeModel titleStyle, bool excel2003 = false)
        {
            IList<string[]> cols = new List<string[]>();
            for (int i = 0; i < sheetNames.Length; i++)
            {
                string[] defaultCols = new string[ds.Tables[i].Columns.Count];
                for (int j = 0; j < defaultCols.Length; j++)
                {
                    defaultCols[j] = ds.Tables[i].Columns[j].ColumnName;
                }
                cols.Add(defaultCols);
            }
            return ExportExcel(ds, sheetNames, cols, cols, null, rList, titleStyle, excel2003);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dt"></param>
        /// <param name="SheetName"></param>
        /// <param name="headerCaption">要导出列的表头数组</param>
        /// <param name="dataColName">要导出数据的字段名数组</param>
        /// <param name="widthCaption"></param>
        /// <param name="rList"></param>
        /// <param name="titleStyle"></param>
        /// <param name="excel2003">导出excel格式，默认为false .xlsx</param>
        /// <returns></returns>
        public static byte[] ExportExcel(DataTable dt, string SheetName, string[] headerCaption, string[] dataColName, string[] widthCaption, IList<CellRangeModel> rList, CellRangeModel titleStyle, bool excel2003 = false)
        {
            DataSet ds = null;
            if (dt != null)
            {
                ds = new DataSet();
                ds.Tables.Add(dt);
            }
            return ExportExcel(ds, new string[] { SheetName }, new List<string[]> { headerCaption }, new List<string[]> { dataColName }, widthCaption, rList, titleStyle, excel2003);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="excel"></param>
        /// <param name="dt"></param>
        /// <param name="SheetName"></param>
        /// <param name="headTitle"></param>
        /// <param name="dataCol"></param>
        /// <param name="widthCol"></param>
        /// <param name="rlist"></param>
        /// <param name="styleModel"></param>
        /// <param name="excel2003"></param>
        internal static void InsertRecord(IWorkbook excel, DataTable dt, string SheetName, string[] headTitle, string[] dataCol, string[] widthCol, IList<CellRangeModel> rlist, CellRangeModel styleModel, bool excel2003)
        {
            if (dt.Rows.Count == 0)
            {
                if (string.IsNullOrEmpty(SheetName))
                    excel.CreateSheet(dt.TableName);
                else
                    excel.CreateSheet(SheetName);
                return;
            }
            int startMerge = rlist.Count > 0 ? 1 : 0;
            //当数据行数超过65535时，则创建多个sheet
            int rows = dt.Rows.Count;

            int sheetCount = 0;
            if (excel2003)
                sheetCount = rows % 65535 == 0 ? rows / 65535 : (rows / 65535) + 1;
            else
                sheetCount = rows % 1048575 == 0 ? rows / 1048575 : (rows / 1048575) + 1;

            for (int l = 0; l < sheetCount; l++)
            {
                string sheetName = string.Empty;
                if (!string.IsNullOrEmpty(SheetName))
                {
                    sheetName = SheetName;
                }
                else if (!string.IsNullOrEmpty(dt.TableName))
                {
                    sheetName = dt.TableName;
                }

                if (sheetCount > 1)
                {
                    sheetName += (l + 1).ToString();
                }
                ISheet sheet = excel.CreateSheet(sheetName);

                #region 字段标题

                IRow rowMerge = sheet.CreateRow(0);
                for (int i = 0; i < headTitle.Length; i++)
                {
                    rowMerge.CreateCell(i).SetCellValue("");
                }

                for (int i = 0; i < rlist.Count; i++)
                {
                    #region 合并行样式

                    //设置样式  加粗，居中
                    ICellStyle style = excel.CreateCellStyle();
                    //设置对齐方式
                    style.Alignment = rlist[i].HorAlign;
                    //设置背景色
                    if (rlist[i].BackGroundColor > 0)
                    {
                        style.FillPattern = FillPattern.SolidForeground;
                        style.FillForegroundColor = rlist[i].BackGroundColor;
                    }
                    IFont font = excel.CreateFont();
                    font.Boldweight = short.MaxValue;
                    //设置字体大小
                    if (int.Parse(rlist[i].FontSize.ToString()) > 0)
                    {
                        font.FontHeightInPoints = rlist[i].FontSize;
                    }

                    //设置字体颜色
                    if (rlist[i].FontColor > 0)
                    {
                        font.Color = rlist[i].FontColor;
                    }
                    style.SetFont(font);
                    #endregion

                    rowMerge.GetCell(rlist[i].ColStart).SetCellValue(rlist[i].Name);
                    rowMerge.GetCell(rlist[i].ColStart).CellStyle = style;
                    sheet.AddMergedRegion(new CellRangeAddress(rlist[i].RowStart, rlist[i].RowEnd, rlist[i].ColStart, rlist[i].ColEnd));
                }

                IRow rowHeader = rlist.Count > 0 ? sheet.CreateRow(1) : sheet.CreateRow(0);

                //rowHeader.HeightInPoints = 20;

                for (int i = 0; i < headTitle.Length; i++)
                {
                    rowHeader.CreateCell(i).SetCellValue(headTitle[i]);
                    if (widthCol != null)
                    {
                        //设置列宽度
                        var w = int.Parse(widthCol[i].ToLower().TrimEnd("px".ToCharArray()));
                        if (w > 0)
                        {
                            sheet.SetColumnWidth(i, w * 36); //乘36接近正常像素值
                        }
                    }

                    #region Title样式

                    //设置样式  加粗，居中
                    ICellStyle titleStyle = excel.CreateCellStyle();
                    //设置对齐方式
                    titleStyle.Alignment = styleModel.HorAlign;
                    //设置背景色
                    if (styleModel.BackGroundColor > 0)
                    {
                        titleStyle.FillPattern = FillPattern.SolidForeground;
                        titleStyle.FillForegroundColor = styleModel.BackGroundColor;
                    }
                    IFont font = excel.CreateFont();
                    font.Boldweight = short.MaxValue;
                    //设置字体颜色
                    if (styleModel.FontColor > 0)
                    {
                        font.Color = styleModel.FontColor;
                    }
                    titleStyle.SetFont(font);
                    #endregion

                    rowHeader.GetCell(i).CellStyle = titleStyle;
                }
                #endregion

                #region 数据行
                int startIndex = l * 60000;
                int endIndex = startIndex + 60000;
                if (endIndex > rows)
                {
                    endIndex = rows;
                }
                for (int j = startIndex; j < endIndex; j++)
                {
                    IRow row = sheet.CreateRow(j - startIndex + 1 + startMerge);
                    for (int k = 0; k < dataCol.Length; k++)
                    {
                        ICell cell = row.CreateCell(k);
                        System.Type rowType = dt.Rows[j][dataCol[k]].GetType();
                        string drValue = dt.Rows[j][dataCol[k]].ToString().Trim();
                        switch (rowType.ToString())
                        {
                            case "System.String"://字符串类型                           
                                cell.SetCellValue(drValue);
                                break;
                            case "System.DateTime"://日期类型
                                DateTime dateV;
                                DateTime.TryParse(drValue, out dateV);
                                cell.SetCellValue(drValue);

                                //格式化显示
                                ICellStyle cellStyle = excel.CreateCellStyle();
                                NPOI.SS.UserModel.IDataFormat format = excel.CreateDataFormat();
                                cellStyle.DataFormat = format.GetFormat("yyyy-mm-dd hh:mm:ss");
                                cell.CellStyle = cellStyle;
                                break;
                            case "System.Boolean"://布尔型
                                bool boolV = false;
                                bool.TryParse(drValue, out boolV);
                                cell.SetCellValue(boolV);
                                break;
                            case "System.Int16"://整型
                            case "System.Int32":
                            case "System.Int64":
                            case "System.Byte":
                                int intV = 0;
                                int.TryParse(drValue, out intV);
                                cell.SetCellValue(intV);
                                break;
                            case "System.Decimal"://浮点型
                            case "System.Double":
                                double doubV = 0;
                                double.TryParse(drValue, out doubV);
                                cell.SetCellValue(doubV);
                                break;
                            default:
                                cell.SetCellValue("");
                                break;
                        }

                    }
                }
                #endregion
            }
        }

        #region 表格合并信息 class

        /// <summary>
        /// 表格合并信息
        /// </summary>
        public sealed partial class CellRangeModel
        {
            /// <summary>
            /// 实例化对象
            /// </summary>
            /// <param name="rowStart"></param>
            /// <param name="rowEnd"></param>
            /// <param name="colStart"></param>
            /// <param name="colEnd"></param>
            /// <param name="name"></param>
            /// <param name="backGroundColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="fontColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="horAlign">NPOI.SS.UserModel.HorizontalAlignment</param>
            public CellRangeModel(int rowStart, int rowEnd, int colStart, int colEnd, string name, short backGroundColor, short fontColor, HorizontalAlignment horAlign)
            {
                this.rowStart = rowStart;
                this.rowEnd = rowEnd;
                this.ColStart = colStart;
                this.colEnd = colEnd;
                this.name = name;
                this.BackGroundColor = backGroundColor;
                this.FontColor = fontColor;
                this.HorAlign = horAlign;
            }

            /// <summary>
            /// 实例化对象
            /// </summary>
            /// <param name="rowStart"></param>
            /// <param name="rowEnd"></param>
            /// <param name="colStart"></param>
            /// <param name="colEnd"></param>
            /// <param name="name"></param>
            /// <param name="backGroundColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="fontColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="horAlign">NPOI.SS.UserModel.HorizontalAlignment</param>
            /// <param name="fontSize"></param>
            public CellRangeModel(int rowStart, int rowEnd, int colStart, int colEnd, string name, short backGroundColor, short fontColor, HorizontalAlignment horAlign, short fontSize)
            {
                this.rowStart = rowStart;
                this.rowEnd = rowEnd;
                this.ColStart = colStart;
                this.colEnd = colEnd;
                this.name = name;
                this.BackGroundColor = backGroundColor;
                this.FontColor = fontColor;
                this.HorAlign = horAlign;
                this.FontSize = fontSize;
            }

            /// <summary>
            /// Title样式设置
            /// </summary>
            /// <param name="backGroundColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="fontColor">NPOI.SS.UserModel.IColor</param>
            /// <param name="horAlign">NPOI.SS.UserModel.HorizontalAlignment</param>
            public CellRangeModel(short backGroundColor, short fontColor, HorizontalAlignment horAlign)
            {
                this.BackGroundColor = backGroundColor;
                this.FontColor = fontColor;
                this.HorAlign = horAlign;
            }

            /// <summary>
            /// 无函数构造
            /// </summary>
            public CellRangeModel()
            {
            }

            private int rowStart;
            public int RowStart
            {
                get { return rowStart; }
                set { rowStart = value; }
            }
            private int rowEnd;
            public int RowEnd
            {
                get { return rowEnd; }
                set { rowEnd = value; }
            }
            private int colStart;
            public int ColStart
            {
                get { return colStart; }
                set { colStart = value; }
            }
            private int colEnd;
            public int ColEnd
            {
                get { return colEnd; }
                set { colEnd = value; }
            }
            private string name;
            public string Name
            {
                get { return name; }
                set { name = value; }
            }

            /// <summary>
            /// 背景色
            /// </summary>
            public short BackGroundColor { get; set; }
            /// <summary>
            /// 字体颜色
            /// </summary>
            public short FontColor { get; set; }
            /// <summary>
            /// 对齐方式
            /// </summary>
            public HorizontalAlignment HorAlign { get; set; }
            /// <summary>
            /// 文字大小
            /// </summary>
            public short FontSize { get; set; }

        }

        #endregion

        #endregion

        #region // webform 导出
        //HttpResponse resp;
        //resp = HttpContext.Current.Response;
        //resp.ContentEncoding = System.Text.Encoding.GetEncoding("UTF-8");
        //resp.AppendHeader("Content-Disposition", "attachment;filename=" + HttpUtility.HtmlEncode(fileName) + ".xls");
        //resp.Charset = "UTF-8";
        //resp.ContentType = "application/ms-excel";
        //excel.Write(resp.OutputStream);
        //resp.Flush();
        //resp.End();
        #endregion

        
    }
}

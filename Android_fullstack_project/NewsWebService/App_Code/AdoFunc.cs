using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;

public static class AdoFunc
{
    //**********************************************************************
    //Veri Tabanı Bağlantısı
    public static SqlConnection Baglan()
    {
        var baglanti = new SqlConnection(ConfigurationManager.ConnectionStrings["RamazanCS"].ConnectionString);
        baglanti.Open();
        return (baglanti);
    }


    //**********************************************************************
    //sql Sorgu Çalıştırma
    public static int Cmd(string sqlcumle)
    {

        var baglan = Baglan();
        var sorgu = new SqlCommand(sqlcumle, baglan);
        int sonuc;

        try
        {
            sonuc = sorgu.ExecuteNonQuery();
        }
        catch (SqlException ex)
        {

            throw new Exception(ex.Message + "(" + sqlcumle + ")");
        }
        finally
        {
            sorgu.Connection.Close();
        }
        sorgu.Dispose();
        baglan.Close();
        return (sonuc);
    }

    //**********************************************************************
    //Kayıt Sayısını Bulma
    public static string GetDataCell(string sql)
    {
        var table = GetDataTable(sql);
        return
            table.Rows.Count == 0 ? null : table.Rows[0][0].ToString();
    }

    //**********************************************************************
    //Kayıt Çekme
    public static DataRow GetDataRow(string sql)
    {
        var table = GetDataTable(sql);
        return table.Rows.Count == 0 ? null : table.Rows[0];
    }


    //**********************************************************************
    //Data Tableye Veri Çekme
    public static DataTable GetDataTable(string sql)
    {
        var baglan = Baglan();
        var adapter = new SqlDataAdapter(sql, baglan);
        var dt = new DataTable();
        try
        {
            adapter.Fill(dt);
        }
        catch (SqlException ex)
        {

            throw new Exception(ex.Message + "(" + sql + ")");
        }
        adapter.Dispose();
        baglan.Close();
        return dt;
    }


    //**********************************************************************
    //Database Veri Çekme
    public static DataSet GetDataSet(string sql)
    {
        var baglan = Baglan();
        var adapter = new SqlDataAdapter(sql, baglan);
        var ds = new DataSet();
        try
        {
            adapter.Fill(ds);
        }
        catch (SqlException ex)
        {

            throw new Exception(ex.Message + "(" + sql + ")");
        }
        adapter.Dispose();
        baglan.Close();
        return ds;
    }


    public static string ClearSql(this string text)
    {

        var strReturn = text.Trim();

        strReturn = strReturn.Replace("&gt;", "");
        strReturn = strReturn.Replace("&lt;", "");
        strReturn = strReturn.Replace("--", "");
        strReturn = strReturn.Replace("'", "");
        strReturn = strReturn.Replace("char ", "");
        strReturn = strReturn.Replace("delete ", "");
        strReturn = strReturn.Replace("insert ", "");
        strReturn = strReturn.Replace("update ", "");
        strReturn = strReturn.Replace("select ", "");
        strReturn = strReturn.Replace("truncate ", "");
        strReturn = strReturn.Replace("union ", "");
        strReturn = strReturn.Replace("script ", "");

        return strReturn;
    }

    public static T Map<T>(DataRow dataRow) where T : new()
    {
        var model = new T();
        var hasProp = typeof(T).GetProperties().Any();
        var colums = dataRow.Table.Columns;
        if (colums.Count == 1 && !hasProp)
            return (T)Convert.ChangeType(dataRow[0], typeof(T));
        foreach (DataColumn colum in colums)
        {
            var value = dataRow[colum.ColumnName];
            var property = model.GetType().GetProperty(colum.ColumnName);
            if (property != null)
                property.SetValue(model, value, null);
        }
        return model;
    }

    public static IEnumerable<T> Map<T>(DataTable dataTable) where T : new()
    {
        var hasProp = typeof(T).GetProperties().Any();
        var colums = dataTable.Rows[0].Table.Columns;
        foreach (DataRow dataRow in dataTable.Rows)
        {
            var model = new T();
            if (colums.Count == 1 && !hasProp)
            {
                yield return (T)Convert.ChangeType(dataRow[0], typeof(T));
                continue;
            }
            foreach (DataColumn colum in colums)
            {
                var value = dataRow[colum.ColumnName];
                var property = model.GetType().GetProperty(colum.ColumnName);
                if (property != null && value != DBNull.Value)
                {
                    property.SetValue(model, value, null);
                }
            }
            yield return model;
        }
    }
    public static Image Base64ToImage(string base64String)
    {
        // Convert base 64 string to byte[]
        byte[] imageBytes = Convert.FromBase64String(base64String);
        // Convert byte[] to Image
        using (var ms = new MemoryStream(imageBytes, 0, imageBytes.Length))
        {
            Image image = Image.FromStream(ms, true);
            return image;
        }
    }
    public static string GetUniqueKey(byte maxSize)
    {
        var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".ToCharArray();
        var data = new byte[1];
        using (var crypto = new RNGCryptoServiceProvider())
        {
            crypto.GetNonZeroBytes(data);
            data = new byte[maxSize];
            crypto.GetNonZeroBytes(data);
        }
        var result = new StringBuilder(maxSize);
        foreach (var b in data)
        {
            result.Append(chars[b % (chars.Length)]);
        }
        return result.ToString();
    }

}
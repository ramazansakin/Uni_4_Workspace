using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Web.Configuration;
using System.Web.Script.Services;
using System.Web.Services;
using Models;

/// <summary>
/// Summary description for MobilService
/// </summary>
[ToolboxItem(false)]
[ScriptService]
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
public class MobileService : WebService
{
    [WebMethod]
    public List<News> List()
    {
        var data = AdoFunc.GetDataTable("select * from News where IsActive=1");
        var models = AdoFunc.Map<News>(data).ToList();
        return models;
    }
    [WebMethod]
    public bool Add(string title, string description, string userName, string token, string base64 = "", string fileName = "")
    {
        if (token != WebConfigurationManager.AppSettings["token"])
            return false;
        const bool isActive = false;
        var filePath = "";
        if (!string.IsNullOrEmpty(base64))
        {
            try
            {
                var path = Path.GetExtension(fileName);
                filePath = AdoFunc.GetUniqueKey(60) + path;
                var serverPath = Server.MapPath("images/" + filePath);
                using (var fs = new FileStream(serverPath, FileMode.Create))
                {
                    using (var bw = new BinaryWriter(fs))
                    {
                        byte[] data = Convert.FromBase64String(base64);
                        bw.Write(data);
                        bw.Close();
                    }
                }
            }
            catch (Exception ex)
            {
                return false;
            }
        }
        return AdoFunc.Cmd(@"insert into News (Title, Description, UserName, IsActive, FilePath) values ('" + title.ClearSql() + "','" + description.ClearSql() + "','" + userName.ClearSql() + "','" + isActive + "','" + filePath.ClearSql() + "')") > 0;
    }
}

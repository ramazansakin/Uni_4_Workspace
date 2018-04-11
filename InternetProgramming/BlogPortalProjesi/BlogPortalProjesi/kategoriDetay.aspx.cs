using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.OleDb;
namespace BlogPortalProjesi
{
    public partial class kategoriDetay : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString.Count <= 0)
            {
                Response.Write("<font color=red>QueryString Girilmedi!");
                Response.Redirect("Anasayfa.aspx");   
            }
       }
        
    }
}
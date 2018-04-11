using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace BlogPortalProjesi
{
    public partial class yonetici_guncelle : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            {
                if (Request.Cookies["yonetici"] == null)
                {
                    Response.Redirect("Anasayfa.aspx");
                }
                else
                {
                    Response.Write("<font color=green><b>Aktif Yönetici Hesaplarının Listesi : </b></font> <br><hr color=#330000/> <br> <fieldset style='background-color:#DEDEDE;'><ul>");
                    SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "select kullanici_adi,kullanici_ID from hesaplar";
                    DataTable tablo = new DataTable();
                    SqlDataAdapter adapt = new SqlDataAdapter(komut);
                    adapt.Fill(tablo);
                    foreach (DataRow bilgi in tablo.Rows)
                    {
                        if (!IsPostBack)
                        {
                            Response.Write("<li>" + "<font  color=green>Kullanıcı ID : </font>" + bilgi["kullanici_ID"].ToString() + " &nbsp; " + "<font color=green>Kullanıcı Adı : </font>" + "   " + bilgi["kullanici_adi"].ToString() + "<a href=\"yonetici_profil.aspx?ID="+bilgi["kullanici_ID"].ToString()+"\">" + "  &nbsp;"+"Güncelle!</a>" + "</li>");                       
                            
                        }
                    }
                    baglanti.Close();
                    baglanti.Dispose();
                    Response.Write("</fieldset></ul><br>");
                    Response.Write("<font color=#330000><b>Not:</b></font> Düzenlemek için 'Güncelle' bağlantısını kullanabilirsiniz.");
                    
                }
            }
        }
    }
}
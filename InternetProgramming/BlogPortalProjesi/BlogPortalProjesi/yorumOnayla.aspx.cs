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
    public partial class yorumOnayla : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else { 
            Response.Write("<font color=green><b>Onay Bekleyen Yorumların Listesi : </b></font> <br><hr color=#330000/> <br> <fieldset style='background-color:#DEDEDE;'><ul>");
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            baglanti.Open();
            SqlCommand komut = new SqlCommand();
            komut.Connection = baglanti;
            komut.CommandType = CommandType.Text;
            komut.CommandText = "select * from yorumlar where yorum_onay_durumu= 0 and istenmeyen= 0";
            DataTable tablo = new DataTable();
            SqlDataAdapter adapt = new SqlDataAdapter(komut);
            adapt.Fill(tablo);
            foreach (DataRow bilgi in tablo.Rows)
            {
                if (!IsPostBack)
                {
                    Response.Write("<li>" + "<font  color=green class=baslik>Yorum Tarihi : </font>" + bilgi["yorum_tarihi"].ToString() + " &nbsp; " + "<font color=green class=baslik> Adı : </font>" + "   " + bilgi["yorumcu_adi"].ToString() + "<font color=green class=baslik> E-posta : </font>" + bilgi["yorumcu_eposta"].ToString() + "<p><font color=green class=baslik>Yorum : </font> " + bilgi["yorum_icerigi"].ToString() + "</p>" + "<br><a href=\"onayla.aspx?ID=" + bilgi["yorum_ID"].ToString() + "\">" + "  &nbsp;" + "<span class=onay>Onayla!</span></a>" + "<a href=\"yorumEtiket.aspx?ID=" + bilgi["yorum_ID"].ToString() + "\">" + "  &nbsp; - &nbsp; " + "<span class=onay>İstenmeyen Olarak Etiketle!</span></a>" + "<a href=\"yorumSil.aspx?ID=" + bilgi["yorum_ID"].ToString() + "\">" + "  &nbsp; - &nbsp; " + "<span class=onay>Sil!</span></a>" + "</li>");
                }
            }
            baglanti.Close();
            baglanti.Dispose();
            Response.Write("</fieldset></ul><br>");
          }
       }
    }
}
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
    public partial class loginn : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] != null)
            {
                Response.Redirect("adminPanel.aspx");
            }
        }

        protected void girisYap_Click(object sender, EventArgs e)
        {
            if (kullaniciAdi.Text != string.Empty && sifre.Value != string.Empty)
            {
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "Select * from hesaplar where kullanici_adi=@kullanici_adi and kullanici_sifre=@kullanici_sifre";
                komut.Parameters.AddWithValue("@kullanici_adi", kullaniciAdi.Text);
                komut.Parameters.AddWithValue("@kullanici_sifre", sifre.Value);
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                if (tablo.Rows.Count >= 1)
                {
                    Response.Cookies["yonetici"].Value = "girisyapti";
                    Response.Cookies["yonetici"].Expires.AddDays(2);
                    Response.Redirect("adminPanel.aspx");
                }
                else
                {
                    hata.Text = "Hata: Lütfen kullanıcı adı ve şifrenizi kontrol ediniz.";
                    hata.Visible = true;
                }
                baglanti.Close();
                baglanti.Dispose();
            }
            else
            {
                hata.Text = "Hata: Bu alanları boş geçemezsiniz.";
                hata.Visible = true;
            }
        }
    }
}
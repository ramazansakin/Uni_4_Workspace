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
    public partial class iletisim_yonetici : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else
            {
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "select * from iletisim";
                    DataTable tablo = new DataTable();
                    SqlDataAdapter adapt = new SqlDataAdapter(komut);
                    adapt.Fill(tablo);
                    foreach (DataRow item in tablo.Rows)
                    {
                        if (!IsPostBack)
                        {
                            iletisim_baslik.Text = item["iletisim_baslik"].ToString();
                            iletisim_icerik.Value = item["iletisim_icerik"].ToString();
                        }
                    }
                }
                catch (Exception ex)
                {
                    Response.Write("<font color=red>HATA:</font>" + ex.Message);
                }
                finally
                {
                    baglanti.Close();
                    baglanti.Dispose();
                }
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "UPDATE iletisim SET iletisim_baslik=@iletisim_baslik,iletisim_icerik=@iletisim_icerik";
                komut.Parameters.AddWithValue("@iletisim_baslik", iletisim_baslik.Text);
                komut.Parameters.AddWithValue("@iletisim_icerik", iletisim_icerik.Value);
                komut.ExecuteNonQuery();
                lbl_iletisim_guncelle.Text = "Bilgiler Kaydedildi!";
                lbl_iletisim_guncelle.Visible = true;
            }
            catch (Exception ex)
            {
                Response.Write("<font color=red>HATA:</font>" + ex.Message);
            }
            finally
            {
                baglanti.Close();
                baglanti.Dispose();
            }
        }
    }
}
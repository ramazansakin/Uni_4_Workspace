using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;

namespace BlogPortalProjesi
{
    public partial class hakkinda_yonetici : System.Web.UI.Page
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
                try { 
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "select * from hakkinda";
                    DataTable tablo = new DataTable();
                    SqlDataAdapter adapt = new SqlDataAdapter(komut);
                    adapt.Fill(tablo);
                    foreach (DataRow item in tablo.Rows)
                    {
                        if (!IsPostBack)
                        {
                            hakkinda_baslik.Text = item["hakkinda_baslik"].ToString();
                            hakkinda_icerik.Value = item["hakkinda_icerik"].ToString();
                        }
                    }
                }
                catch (Exception ex)
                {
                    Response.Write("<font color=red>HATA:</font>" + ex.Message);
                }
                finally {
                    baglanti.Close();
                    baglanti.Dispose();
                }
            }
        }


        protected void Button1_Click(object sender, EventArgs e)
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "UPDATE hakkinda SET hakkinda_baslik=@hakkinda_baslik,hakkinda_icerik=@hakkinda_icerik";
                komut.Parameters.AddWithValue("@hakkinda_baslik",hakkinda_baslik.Text);
                komut.Parameters.AddWithValue("@hakkinda_icerik",hakkinda_icerik.Value);
                komut.ExecuteNonQuery();
                lbl_hakkinda_guncelle.Text = "Bilgiler Kaydedildi!";
                lbl_hakkinda_guncelle.Visible = true;
            }
            catch(Exception ex) {
                Response.Write("<font color=red>HATA:</font>" + ex.Message);
            }
            finally {
                baglanti.Close();
                baglanti.Dispose();
            }
        }
    }
}
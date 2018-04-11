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
    public partial class yonetici_ekle : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            bosAlan.Visible = false;
        }

        protected void btn_ekle_Click(object sender, EventArgs e)
        {
            if (txt_yon_adi.Text!=string.Empty && txt_yon_sifre.Value!=string.Empty)
            {
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "INSERT INTO hesaplar (kullanici_adi,kullanici_sifre) VALUES (@kullanici_adi,@kullanici_sifre)";
                    komut.Parameters.AddWithValue("@kullanici_adi", txt_yon_adi.Text);
                    komut.Parameters.AddWithValue("@kullanici_sifre", txt_yon_sifre.Value);
                    komut.ExecuteNonQuery();
                    lbl_eklendi.Visible = true;
                }

                catch (Exception ex)
                {
                    lbl_eklenmedi.Visible = true;
                }

                finally
                {
                    if (baglanti.State == ConnectionState.Open)
                    {
                        baglanti.Close();
                    }
                }

                txt_yon_adi.Text = string.Empty;
                txt_yon_sifre.Value = string.Empty;
            }
            else
            {
                bosAlan.Visible = true;
            }
        }
    }
}
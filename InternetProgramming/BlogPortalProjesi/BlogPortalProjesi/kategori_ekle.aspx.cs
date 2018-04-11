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
    public partial class kategori_ekle : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (kategori_adi.Text!=string.Empty) {

                // kategori için veri tabanı kodlarının başlangıcı.
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "INSERT INTO kategoriler (kategori_adi) VALUES (@kategori_adi)";
                    komut.Parameters.AddWithValue("@kategori_adi", kategori_adi.Text);
                    komut.ExecuteNonQuery();
                }

                catch (Exception ex)
                {
                    Literal1.Visible = true;
                }

                finally
                {
                    if (baglanti.State == ConnectionState.Open)
                    {
                        baglanti.Close();
                    }
                }
                // kategori kaydı sonu.
                kategori_adi.Text = string.Empty;
                Response.Redirect("kategori_ekle.aspx");
            }
            else
            {
                Literal2.Visible = true;
            }
        }

        protected void btn_kategori_sil_Click(object sender, EventArgs e)
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "DELETE FROM kategoriler WHERE kategori_adi=@kategori_soyadi";
                    komut.Parameters.AddWithValue("@kategori_soyadi",kategori_list.SelectedValue);
                    komut.ExecuteNonQuery();
                }

                catch (Exception ex)
                {
                    Response.Write(ex.Message);
                }

                finally
                {
                    if (baglanti.State == ConnectionState.Open)
                    {
                        baglanti.Close();
                    }
                    Response.Redirect("kategori_ekle.aspx");
                }
            }

        protected void kategori_list_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        protected void kategori_guncelle_listesi_SelectedIndexChanged(object sender, EventArgs e)
        {
            kategori_duzen.Text = kategori_guncelle_listesi.SelectedValue;
        }

        protected void btn_guncele_panpa_Click(object sender, EventArgs e)
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "UPDATE kategoriler SET kategori_adi=@kategoriAdi WHERE kategori_adi =@kategori_orjinal";
                komut.Parameters.AddWithValue("@kategoriAdi", kategori_duzen.Text);
                komut.Parameters.AddWithValue("@kategori_orjinal", kategori_guncelle_listesi.SelectedValue);
                komut.ExecuteNonQuery();
              }

            catch (Exception ex)
            {
                Response.Write(ex.Message);
            }

            finally
            {
                if (baglanti.State == ConnectionState.Open)
                {
                    baglanti.Close();
                }
                Response.Redirect("kategori_ekle.aspx");
            }
        }

        }
    }

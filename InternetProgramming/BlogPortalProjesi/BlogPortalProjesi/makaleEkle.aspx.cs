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
    public partial class makaleEkle : System.Web.UI.Page
    {
        string deger = string.Empty;
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
                    komut.CommandText = "select kategori_ID from kategoriler where kategori_adi=@kategori_adi";
                    komut.Parameters.AddWithValue("kategori_adi", kategoriTurleri.SelectedValue);
                    DataTable tablo = new DataTable();
                    SqlDataAdapter adapt = new SqlDataAdapter(komut);
                    adapt.Fill(tablo);
                    foreach (DataRow bilgi in tablo.Rows)
                    {
                        deger = bilgi["kategori_ID"].ToString();
                    }
                }
                catch (Exception ex)
                {
                    Response.Write(ex.Message);
                }
                finally
                {
                    baglanti.Close();
                    baglanti.Dispose();
                }
            }
        }

        protected void btn_makale_kydt_Click(object sender, EventArgs e)
        {
            if (txt_kat_baslik.Text != string.Empty && txt_mkl_icerigi.Value != string.Empty)
            {
                // Makale kaydı için veri tabanı kodlarının başlangıcı.
                string mkl_ozt = string.Empty;

                if (txt_mkl_icerigi.Value.Length > 500)
                {
                    mkl_ozt = txt_mkl_icerigi.Value.Substring(0, 497) + "...";
                }
                else
                {
                    mkl_ozt = txt_mkl_icerigi.Value;
                }
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "INSERT INTO makaleler (makale_basligi,makale_tarihi,makale_icerigi,kategori_ID,makale_etiketleri,makale_ozet) VALUES (@makale_basligi,@makale_tarihi,@makale_icerigi,@kategori_ID,@makale_etiketleri,@makale_ozet)";
                    komut.Parameters.AddWithValue("@makale_basligi", txt_kat_baslik.Text);
                    komut.Parameters.AddWithValue("@makale_tarihi", DateTime.Now.ToString());
                    komut.Parameters.AddWithValue("@makale_icerigi", txt_mkl_icerigi.Value);
                    komut.Parameters.AddWithValue("@kategori_ID", deger);
                    komut.Parameters.AddWithValue("@makale_etiketleri", txt_makale_etiket.Text);
                    komut.Parameters.AddWithValue("@makale_ozet", mkl_ozt);
                    komut.ExecuteNonQuery();
                    txt_makale_etiket.Text = string.Empty;
                    Response.Write("<font color=green>Kayıt Başarılı!</font>");

                }

                catch (Exception ex)
                {
                    Response.Write("<font color=red>Hata : </font>" + ex.Message);
                }

                finally
                {
                    if (baglanti.State == ConnectionState.Open)
                    {
                        baglanti.Close();
                    }
                }

                // Makale kaydı sonu.
                txt_kat_baslik.Text = "";
                txt_mkl_icerigi.Value = "";
            }
            else
            {
                Label5.Text = "<font color=red>Hata : </font>Alanları boş geçemezsiniz!";
                Label5.Visible = true;
            }
        }

        protected void kategoriTurleri_SelectedIndexChanged1(object sender, EventArgs e)
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "select kategori_ID from kategoriler where kategori_adi=@kategori_adi";
                komut.Parameters.AddWithValue("kategori_adi",kategoriTurleri.SelectedValue);
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                foreach (DataRow bilgi in tablo.Rows)
                {
                    deger = bilgi["kategori_ID"].ToString();
                }
            }
            catch (Exception ex)
            {
                Response.Write(ex.Message);
            }
            finally
            {
                baglanti.Close();
                baglanti.Dispose();
            }
        }
    }
}

          

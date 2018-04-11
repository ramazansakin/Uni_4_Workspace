namespace WindowsFormsApplication3
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.deger1TB = new System.Windows.Forms.TextBox();
            this.deger2TB = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.DegerlerLw = new System.Windows.Forms.ListView();
            this.Sıra = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.X1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.X2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.status = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.sonuclarLw = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.sonucDeger1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SonucDeger2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Uzaklık = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Sonuc = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button2 = new System.Windows.Forms.Button();
            this.posRdBtn = new System.Windows.Forms.RadioButton();
            this.negRdBtn = new System.Windows.Forms.RadioButton();
            this.kValue = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.resultK = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.istenilenX2 = new System.Windows.Forms.TextBox();
            this.istenilenX1 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(223, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(166, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Ramazan Sakin -- 201413171809";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // deger1TB
            // 
            this.deger1TB.Location = new System.Drawing.Point(34, 53);
            this.deger1TB.Name = "deger1TB";
            this.deger1TB.Size = new System.Drawing.Size(100, 20);
            this.deger1TB.TabIndex = 1;
            // 
            // deger2TB
            // 
            this.deger2TB.Location = new System.Drawing.Point(34, 103);
            this.deger2TB.Name = "deger2TB";
            this.deger2TB.Size = new System.Drawing.Size(100, 20);
            this.deger2TB.TabIndex = 2;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(31, 87);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(45, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "Değer-2";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(65, 145);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(38, 13);
            this.label4.TabIndex = 5;
            this.label4.Text = "Durum";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(47, 193);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 8;
            this.button1.Text = "Gözlem Ekle";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // DegerlerLw
            // 
            this.DegerlerLw.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Sıra,
            this.X1,
            this.X2,
            this.status});
            this.DegerlerLw.FullRowSelect = true;
            this.DegerlerLw.Location = new System.Drawing.Point(179, 37);
            this.DegerlerLw.Name = "DegerlerLw";
            this.DegerlerLw.Size = new System.Drawing.Size(248, 289);
            this.DegerlerLw.TabIndex = 9;
            this.DegerlerLw.UseCompatibleStateImageBehavior = false;
            this.DegerlerLw.View = System.Windows.Forms.View.Details;
            // 
            // Sıra
            // 
            this.Sıra.Text = "Gözlem";
            this.Sıra.Width = 58;
            // 
            // X1
            // 
            this.X1.Text = "X1";
            this.X1.Width = 65;
            // 
            // X2
            // 
            this.X2.Text = "X2";
            this.X2.Width = 73;
            // 
            // status
            // 
            this.status.Text = "Durum";
            this.status.Width = 47;
            // 
            // sonuclarLw
            // 
            this.sonuclarLw.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.sonucDeger1,
            this.SonucDeger2,
            this.Uzaklık,
            this.Sonuc});
            this.sonuclarLw.FullRowSelect = true;
            this.sonuclarLw.Location = new System.Drawing.Point(443, 37);
            this.sonuclarLw.Name = "sonuclarLw";
            this.sonuclarLw.Size = new System.Drawing.Size(305, 289);
            this.sonuclarLw.TabIndex = 10;
            this.sonuclarLw.UseCompatibleStateImageBehavior = false;
            this.sonuclarLw.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Sıra";
            this.columnHeader1.Width = 34;
            // 
            // sonucDeger1
            // 
            this.sonucDeger1.Text = "X1";
            this.sonucDeger1.Width = 57;
            // 
            // SonucDeger2
            // 
            this.SonucDeger2.Text = "X2";
            this.SonucDeger2.Width = 64;
            // 
            // Uzaklık
            // 
            this.Uzaklık.Text = "Uzaklık";
            this.Uzaklık.Width = 79;
            // 
            // Sonuc
            // 
            this.Sonuc.Text = "Sonuc";
            this.Sonuc.Width = 67;
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(24, 321);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(124, 23);
            this.button2.TabIndex = 11;
            this.button2.Text = "En Yakın K komsuluk";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // posRdBtn
            // 
            this.posRdBtn.AutoSize = true;
            this.posRdBtn.Checked = true;
            this.posRdBtn.Location = new System.Drawing.Point(57, 161);
            this.posRdBtn.Name = "posRdBtn";
            this.posRdBtn.Size = new System.Drawing.Size(31, 17);
            this.posRdBtn.TabIndex = 12;
            this.posRdBtn.TabStop = true;
            this.posRdBtn.Text = "+";
            this.posRdBtn.UseVisualStyleBackColor = true;
            // 
            // negRdBtn
            // 
            this.negRdBtn.AutoSize = true;
            this.negRdBtn.Location = new System.Drawing.Point(94, 161);
            this.negRdBtn.Name = "negRdBtn";
            this.negRdBtn.Size = new System.Drawing.Size(28, 17);
            this.negRdBtn.TabIndex = 13;
            this.negRdBtn.Text = "-";
            this.negRdBtn.UseVisualStyleBackColor = true;
            // 
            // kValue
            // 
            this.kValue.Location = new System.Drawing.Point(81, 295);
            this.kValue.Name = "kValue";
            this.kValue.Size = new System.Drawing.Size(67, 20);
            this.kValue.TabIndex = 14;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(31, 37);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(45, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Değer-1";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(29, 298);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(46, 13);
            this.label5.TabIndex = 15;
            this.label5.Text = "K değeri";
            // 
            // resultK
            // 
            this.resultK.Location = new System.Drawing.Point(490, 332);
            this.resultK.Name = "resultK";
            this.resultK.ReadOnly = true;
            this.resultK.Size = new System.Drawing.Size(100, 20);
            this.resultK.TabIndex = 16;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(440, 335);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(44, 13);
            this.label6.TabIndex = 17;
            this.label6.Text = "Sonuç :";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(44, 228);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(78, 13);
            this.label7.TabIndex = 18;
            this.label7.Text = "İstenilen Nokta";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(21, 273);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(20, 13);
            this.label8.TabIndex = 19;
            this.label8.Text = "X2";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(21, 250);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(20, 13);
            this.label9.TabIndex = 20;
            this.label9.Text = "X1";
            // 
            // istenilenX2
            // 
            this.istenilenX2.Location = new System.Drawing.Point(81, 273);
            this.istenilenX2.Name = "istenilenX2";
            this.istenilenX2.Size = new System.Drawing.Size(67, 20);
            this.istenilenX2.TabIndex = 21;
            // 
            // istenilenX1
            // 
            this.istenilenX1.Location = new System.Drawing.Point(81, 250);
            this.istenilenX1.Name = "istenilenX1";
            this.istenilenX1.Size = new System.Drawing.Size(67, 20);
            this.istenilenX1.TabIndex = 22;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(760, 356);
            this.Controls.Add(this.istenilenX1);
            this.Controls.Add(this.istenilenX2);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.resultK);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.kValue);
            this.Controls.Add(this.negRdBtn);
            this.Controls.Add(this.posRdBtn);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.sonuclarLw);
            this.Controls.Add(this.DegerlerLw);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.deger2TB);
            this.Controls.Add(this.deger1TB);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Veri Madenciliği -- Ödev-5";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox deger1TB;
        private System.Windows.Forms.TextBox deger2TB;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ListView DegerlerLw;
        private System.Windows.Forms.ColumnHeader Sıra;
        private System.Windows.Forms.ColumnHeader X1;
        private System.Windows.Forms.ColumnHeader X2;
        private System.Windows.Forms.ColumnHeader status;
        private System.Windows.Forms.ListView sonuclarLw;
        private System.Windows.Forms.ColumnHeader sonucDeger1;
        private System.Windows.Forms.ColumnHeader SonucDeger2;
        private System.Windows.Forms.ColumnHeader Uzaklık;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ColumnHeader Sonuc;
        private System.Windows.Forms.RadioButton posRdBtn;
        private System.Windows.Forms.RadioButton negRdBtn;
        private System.Windows.Forms.TextBox kValue;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.TextBox resultK;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox istenilenX2;
        private System.Windows.Forms.TextBox istenilenX1;
    }
}


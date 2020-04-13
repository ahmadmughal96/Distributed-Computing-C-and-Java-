namespace SNTPClientTester
{
    partial class FormMain
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
            this.buttonQueryServer = new System.Windows.Forms.Button();
            this.propertyGrid = new System.Windows.Forms.PropertyGrid();
            this.sntpClient = new DaveyM69.Components.SNTPClient();
            this.listView = new System.Windows.Forms.ListView();
            this.columnHeader1 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader2 = new System.Windows.Forms.ColumnHeader();
            this.labelSettings = new System.Windows.Forms.Label();
            this.labelCurrentOffset = new System.Windows.Forms.Label();
            this.lnkNow = new System.Windows.Forms.LinkLabel();
            this.SuspendLayout();
            // 
            // buttonQueryServer
            // 
            this.buttonQueryServer.Location = new System.Drawing.Point(12, 224);
            this.buttonQueryServer.Name = "buttonQueryServer";
            this.buttonQueryServer.Size = new System.Drawing.Size(77, 23);
            this.buttonQueryServer.TabIndex = 0;
            this.buttonQueryServer.Text = "&Query Server";
            this.buttonQueryServer.UseVisualStyleBackColor = true;
            this.buttonQueryServer.Click += new System.EventHandler(this.buttonQueryServer_Click);
            // 
            // propertyGrid
            // 
            this.propertyGrid.Location = new System.Drawing.Point(12, 25);
            this.propertyGrid.Name = "propertyGrid";
            this.propertyGrid.SelectedObject = this.sntpClient;
            this.propertyGrid.Size = new System.Drawing.Size(293, 193);
            this.propertyGrid.TabIndex = 3;
            this.propertyGrid.ToolbarVisible = false;
            // 
            // sntpClient
            // 
            this.sntpClient.QueryServerCompleted += new System.EventHandler<DaveyM69.Components.SNTP.QueryServerCompletedEventArgs>(this.sntpClient_QueryServerCompleted);
            // 
            // listView
            // 
            this.listView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2});
            this.listView.FullRowSelect = true;
            this.listView.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.listView.Location = new System.Drawing.Point(311, 12);
            this.listView.MultiSelect = false;
            this.listView.Name = "listView";
            this.listView.Size = new System.Drawing.Size(421, 307);
            this.listView.TabIndex = 4;
            this.listView.TabStop = false;
            this.listView.UseCompatibleStateImageBehavior = false;
            this.listView.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Action / Property";
            this.columnHeader1.Width = 165;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "Result / Data";
            this.columnHeader2.Width = 235;
            // 
            // labelSettings
            // 
            this.labelSettings.AutoSize = true;
            this.labelSettings.Location = new System.Drawing.Point(12, 9);
            this.labelSettings.Name = "labelSettings";
            this.labelSettings.Size = new System.Drawing.Size(106, 13);
            this.labelSettings.TabIndex = 2;
            this.labelSettings.Text = "SNTP Client &Settings";
            // 
            // labelCurrentOffset
            // 
            this.labelCurrentOffset.AutoSize = true;
            this.labelCurrentOffset.Location = new System.Drawing.Point(12, 306);
            this.labelCurrentOffset.Name = "labelCurrentOffset";
            this.labelCurrentOffset.Size = new System.Drawing.Size(100, 13);
            this.labelCurrentOffset.TabIndex = 5;
            this.labelCurrentOffset.Text = "Current UTC Offset:";
            // 
            // lnkNow
            // 
            this.lnkNow.AutoSize = true;
            this.lnkNow.Location = new System.Drawing.Point(12, 270);
            this.lnkNow.Name = "lnkNow";
            this.lnkNow.Size = new System.Drawing.Size(66, 13);
            this.lnkNow.TabIndex = 1;
            this.lnkNow.TabStop = true;
            this.lnkNow.Text = "Display Now";
            this.lnkNow.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkNow_LinkClicked);
            // 
            // FormMain
            // 
            this.AcceptButton = this.buttonQueryServer;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(744, 331);
            this.Controls.Add(this.lnkNow);
            this.Controls.Add(this.labelCurrentOffset);
            this.Controls.Add(this.labelSettings);
            this.Controls.Add(this.listView);
            this.Controls.Add(this.propertyGrid);
            this.Controls.Add(this.buttonQueryServer);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FormMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "SNTPClient Tester";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DaveyM69.Components.SNTPClient sntpClient;
        private System.Windows.Forms.Button buttonQueryServer;
        private System.Windows.Forms.PropertyGrid propertyGrid;
        private System.Windows.Forms.ListView listView;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.Label labelSettings;
        private System.Windows.Forms.Label labelCurrentOffset;
        private System.Windows.Forms.LinkLabel lnkNow;
    }
}
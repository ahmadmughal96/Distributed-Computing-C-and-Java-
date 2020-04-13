using System;
using System.Drawing;
using System.Windows.Forms;
using DaveyM69.Components;
using DaveyM69.Components.SNTP;

namespace SNTPClientTester
{
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();
            UpdateOffsetLabel();
        }

        private void buttonQueryServer_Click(object sender, EventArgs e)
        {
            UpdateOffsetLabel();
            listView.Items.Clear();
            listView.Items.Add(new ListViewItem(new string[] { "Querying server...",
                sntpClient.RemoteSNTPServer.ToString() }));
            if (!sntpClient.QueryServerAsync())
                MessageBox.Show(this, "The SNTP client is busy, please wait.", "SNTP Client Busy",
                    MessageBoxButtons.OK, MessageBoxIcon.Hand);
        }

        private void lnkNow_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            DateTime nowDateTime = 
                SNTPClient.GetNow(sntpClient.RemoteSNTPServer, sntpClient.Timeout);
            string caption = "Current Date & Time";
            string text = string.Empty;
            if (nowDateTime != DateTime.MinValue)
                text = string.Format("The real local date and time is:{0}{0}{1}",
                    Environment.NewLine, nowDateTime);
            else
                text = "There was a problem retrieving the date and time.";
            MessageBox.Show(this, text, caption,
                    MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        void sntpClient_QueryServerCompleted(object sender, QueryServerCompletedEventArgs e)
        {
            if (e.Succeeded)
            {
                listView.Items.Clear();
                listView.Items.AddRange(new ListViewItem[]{
                    new ListViewItem(new string[]
                    { "Query Suceeded:", sntpClient.RemoteSNTPServer.ToString() }),
                    new ListViewItem(new string[]
                    { "IP endpoint:", sntpClient.RemoteSNTPServer.GetIPEndPoint().ToString() }),
                    new ListViewItem(new string[]
                    { "Leap indicator:", e.Data.LeapIndicatorText }),
                    new ListViewItem(new string[]
                    { "Version number:", e.Data.VersionNumberText }),
                    new ListViewItem(new string[]
                    { "Mode:", e.Data.ModeText }),
                    new ListViewItem(new string[]
                    { "Stratum:", e.Data.StratumText }),
                    new ListViewItem(new string[]
                    { "Poll interval (seconds):", e.Data.PollInterval.ToString() }),
                    new ListViewItem(new string[]
                    { "Precision (seconds):", e.Data.Precision.ToString() }),
                    new ListViewItem(new string[]
                    { "Root delay (seconds):", e.Data.RootDelay.ToString() }),
                    new ListViewItem(new string[]
                    { "Root dispersion (seconds):", e.Data.RootDispersion.ToString() }),
                    new ListViewItem(new string[]
                    { "Reference identifier:", e.Data.ReferenceIdentifier }),
                    new ListViewItem(new string[]
                    { "Reference date & time:", string.Format("{0}.{1}",
                        e.Data.ReferenceDateTime, e.Data.ReferenceDateTime.Millisecond) }),
                    new ListViewItem(new string[]
                    { "Originate date & time:", string.Format("{0}.{1}",
                        e.Data.OriginateDateTime, e.Data.OriginateDateTime.Millisecond) }),
                    new ListViewItem(new string[]
                    { "Receive date & time:", string.Format("{0}.{1}",
                        e.Data.ReceiveDateTime, e.Data.ReceiveDateTime.Millisecond) }),
                    new ListViewItem(new string[]
                    { "Transmit date & time:", string.Format("{0}.{1}",
                        e.Data.TransmitDateTime, e.Data.TransmitDateTime.Millisecond) }),
                    new ListViewItem(new string[]
                    { "Destination date & time:", string.Format("{0}.{1}",
                        e.Data.TransmitDateTime, e.Data.TransmitDateTime.Millisecond) }),
                    new ListViewItem(new string[]
                    { "Round trip delay (seconds)", e.Data.RoundTripDelay.ToString() }),
                    new ListViewItem(new string[]
                    { "Local clock offset (seconds)", e.Data.LocalClockOffset.ToString() }),
                    });
            }
            if (e.LocalDateTimeUpdated)
            {
                listView.Items.Add(new ListViewItem(new string[] 
                    { "Local date & time updated. Now:", DateTime.Now.ToString() }));
            }
            if (e.ErrorData.Error)
                listView.Items.Add(new ListViewItem(new string[] { "Error:", e.ErrorData.ErrorText }));
        }

        private void UpdateOffsetLabel()
        {
            TimeSpan offset = SNTPClient.GetCurrentLocalTimeZoneOffset();
            if (offset == TimeSpan.Zero)
                labelCurrentOffset.ForeColor = Color.Green;
            else
                labelCurrentOffset.ForeColor = Color.Red;
            labelCurrentOffset.Text = string.Format("Current UTC Offset: {0}", offset);
        }
    }
}

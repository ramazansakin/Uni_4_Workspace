using System;

namespace Models
{
    public class News
    {
        public int NewsId { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public DateTime DateTime { get; set; }
        public string UserName { get; set; }
        public bool IsActive { get; set; }
        public string FilePath { get; set; }
    }
}
using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;

namespace assignment_02_20141564
{
    class Canvas
    {
        
        Dictionary<char, Shape> myShapes = new Dictionary<char, Shape>();
        double width, height;
        string alphabet = "abcdefghijklmnoprstuvwxyz";

        public string WhereTo { get; set; }

        //singleton pattern to allow only one instance of this class
        private static Canvas instance = new Canvas();
        private Canvas() {}

        public static Canvas Instance 
        {
            get { return instance; }
        }
        public Canvas (double w, double h)
        {
            width = w; 
            height = h;
        }

        public void addShape (char index, Shape e)
        {
            myShapes.Add(index, e);
        }

        public void removeShape (Shape e)
        {
            myShapes.Remove(e.IndexLetter);
        }

        public void removeShape (char index)
        {
            Char.ToLower(index);
            int nb = 0;
            while(index != alphabet[nb])
            {
                nb++;
            }
            myShapes.Remove(index);
        }

        public void updateShape (Shape e, Shape f)
        {
            myShapes.Remove(e.IndexLetter);
            myShapes.Add(e.IndexLetter, f);
        }

        public void canvasToSVG()
        {
            System.IO.File.WriteAllText(WhereTo, string.Empty);
            using (StreamWriter sw = File.CreateText(WhereTo))
            {  
                sw.Write("<svg width=\"" + width +
                "\" height=\"" + height +
                "\">\n");
                for(int i = 0; i<alphabet.Length; i++)
                {
                    foreach(KeyValuePair<char, Shape> s in myShapes)
                    {
                        if(s.Key == alphabet[i])
                        {
                            sw.Write(s.Value.shapeToSVG());
                        }
                    }
                }
               sw.WriteLine("</svg>");
            }
        }
    }
}

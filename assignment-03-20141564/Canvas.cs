using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;

namespace assignment_02_20141564
{
    //Originator class - design pattern
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
            Console.WriteLine(e.toString() + "added to canvas.");
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

        public void removeAll ()
        {
            myShapes.Clear();
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

        public void toString()
        {
            Console.Write("<svg width=\"" + width +
                "\" height=\"" + height +
                "\">\n");
            for(int i = 0; i<alphabet.Length; i++)
            {
                foreach(KeyValuePair<char, Shape> s in myShapes)
                {
                    if(s.Key == alphabet[i])
                    {
                        Console.Write(s.Value.shapeToSVG());
                    }
                }
            }
            Console.WriteLine("</svg>");
        }

        public void set(Dictionary<char, Shape> myNext)
        {
            myShapes = myNext;
        }

        public Dictionary<char, Shape> getShapes()
        {
            Dictionary<char, Shape> ret = new Dictionary<char, Shape>();
            foreach (char key in myShapes.Keys)
            {
                ret.Add(key, myShapes[key]);
            }
            return ret;
        }

        //creates new CanvasMemento object using current Shapes
        public CanvasMemento save()
        {
            return new CanvasMemento(getShapes());
        }

        public Dictionary<char,Shape> revert(CanvasMemento canvasMemento)
        {
            return canvasMemento.getShapes();
        }
        
        //memento class - design pattern
        public class CanvasMemento
        {
            Dictionary<char, Shape> innerShapes;
            
            //save new shapes 
            public CanvasMemento(Dictionary<char,Shape> innerShapes)
            {
                this.innerShapes = innerShapes;
            }

            //returns value stored in innerShapes
            public Dictionary<char,Shape> getShapes()
            {
                return innerShapes;
            }
        }
    }
}

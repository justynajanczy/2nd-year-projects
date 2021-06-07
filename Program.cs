﻿using System;
using System.IO;
using System.Collections.Generic;
using System.Text.RegularExpressions;

//windows VS Code

namespace assignment_01_20141564
{
    class Tabular
    {
        public static string[] arr;
        static string input, output;
        static bool verboseMode = false;
        static string inputExt, outputExt;
        
        //constructor
        public Tabular(string [] myArr) { arr = myArr; }
         

        public void checkCLI()
        {
            bool check = false;
            string prevElem = null;

            //checks if user provided info what to do 
            if (arr.Length == 0) { Console.WriteLine("Provide some arguments"); }
            

            //checks every argument provided in CLI and reacts properly 
            foreach(string x in arr)
            {
                if (x == "-h" || x == "-help")
                {
                    Console.WriteLine("TOPIC");
                    Console.WriteLine("Object-oriented console solution" +
                    "for managing and converting Tabular markup information.");
                    Console.WriteLine("LONG DESCRIPTION");
                    Console.WriteLine(@"-v, -verbose  Verbose mode (debugging output to STDOUT
                                        -o <file2>, -output=<file>  Output file specified by <file>
                                        -l, -list-formats  List formats
                                        -h, -help  Show usage message
                                        -i, -info Show version information");
                    Console.WriteLine("Output file will be deleted everytime writing there new tabular data");

                    check = true;
                }
                if (x =="-i" || x == "-info")
                {
                    Console.WriteLine("Version from 2020-10-16");
                    check = true;
                }
                if (x == "-v" || x == "-verbose")
                {
                    Console.WriteLine("Verbose mode turned on");
                    verboseMode = true;
                    check = true;
                }
                if(x == "-l" || x == "-list-formats")
                {
                    Console.WriteLine("Formats: html, md, csv, json");
                    check = true;
                }
                if(x == "-o")
                {
                    input = prevElem;
                    inputExt = input.Split(".")[1];
                    check = true;
                }
                if(prevElem == "-o")
                {
                    output = x;
                    outputExt =  output.Split(".")[1];
                    check = true;
                }
                if(x.Contains("-output="))
                {
                    input = prevElem;
                    output = x.Substring(9);
                    outputExt = output.Split(".")[1];
                    check = true;
                }
                prevElem = x;
            }
            //what if empty input file
            if( new FileInfo(input).Length == 0)
            {
                Console.WriteLine(new System.ArgumentException("Input file cannot be null"));
            }
            //checks if any of the commands is used
            if(check == false)
            {
                Console.WriteLine(new Exception("None of the arguments matchany of the commands"));
            }  
            if(verboseMode) Console.WriteLine("Checking the correctness of input");
            if(!checkExtension(inputExt) || !checkExtension(outputExt))    
            {
                Console.WriteLine(new FileNotFoundException("File not found"));
            } 

            if(verboseMode) Console.WriteLine("Checking what kind of transformation to make");
            //CSV to other formats
            if(inputExt == "csv")
            {
                switch(outputExt)
                {
                    case "md":
                    MDwrite(CSVread(input), output);
                    break;
                    case "json":
                    JSONwrite(CSVread(input),output);
                    break;
                    case "html":
                    HTMLwrite(CSVread(input),output);
                    break;
                    case "csv":
                    if(FileEquals(input,output))
                    Console.WriteLine("Content of the files is equal");
                    break;
                } 
            }
            //MD to other formats
            if(inputExt == "md")
            {
                switch(outputExt)
                {
                    case "csv":
                    CSVwrite(MDread(input), output);
                    break;
                    case "json":
                    JSONwrite(MDread(input),output);
                    break;
                    case "html":
                    HTMLwrite(MDread(input),output);
                    break;
                    case "md":
                    if(FileEquals(input,output))
                    Console.WriteLine("Content of the files is equal");
                    break;
                } 
            }
            //JSON to other formats
            if(inputExt == "json")
            {
                switch(outputExt)
                {
                    case "csv":
                    CSVwrite(JSONread(input),output);
                    break;
                    case "md":
                    MDwrite(JSONread(input), output);
                    break;
                    case "html":
                    HTMLwrite(JSONread(input),output);
                    break;
                    case "json":
                    if(FileEquals(input,output))
                    Console.WriteLine("Content of the files is equal");
                    break;
                } 
            }
            //html to other formats
            if(inputExt == "html")
            {
                switch(outputExt)
                {
                    case "csv":
                    CSVwrite(HTMLread(input),output);
                    break;
                    case "md":
                    MDwrite(HTMLread(input), output);
                    break;
                    case "json":
                    JSONwrite(HTMLread(input),output);
                    break;
                    case "html":
                    if(FileEquals(input,output))
                    Console.WriteLine("Content of the files is equal");
                    break;
                } 
            }
        }
        
        static bool FileEquals(string path1, string path2)
        {
            byte[] file1 = File.ReadAllBytes(path1);
            byte[] file2 = File.ReadAllBytes(path2);
            if (file1.Length == file2.Length)
            {
                for (int i = 0; i < file1.Length; i++)
                {
                    if (file1[i] != file2[i])
                    {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        static bool checkExtension (string extension)
        {
            if(verboseMode) Console.WriteLine("Checking file extensions");
            string [] extensions = {"csv", "md", "json", "html"};
            foreach(string x in extensions)
            {
                if(extension.Equals(x)){ return true;}
            }
            return false;
        }

        //methods for convertion
        static List<string[]> CSVread (string input)
        {   
            if(verboseMode) Console.WriteLine("Reading CSV file");
            List<string[]> fileArr = new List<string[]>();
            string pattern = @"['""]?";

            using (StreamReader sr = new StreamReader(input))
            {
                string line;
                while((line = sr.ReadLine()) != null)
                {
                    line = Regex.Replace(line, pattern, "");
                    string [] arr = line.Split(", ");
                    for(int i = 0; i<arr.Length; i++){ arr[i] = arr[i].Trim(); }
                    fileArr.Add(arr);
                }
            }
            return fileArr;
        }

        static List<string[]> MDread (string input)
        {
            if(verboseMode) Console.WriteLine("Reading MD file");
            List<string[]> fileArr = new List<string[]>();

            using (StreamReader sr = new StreamReader(input))
            {
                string line;
                while((line = sr.ReadLine()) != null)
                {
                    //delete "" properties
                    List<string> list = new List<string>(line.Split("|"));
                    list.RemoveAt(0);
                    list.RemoveAt(list.Count-1);
                    string[] myArr = list.ToArray();
                    for(int i = 0; i<myArr.Length; i++)
                    {
                        myArr[i] = myArr[i].Trim();
                    }
                    
                    fileArr.Add(myArr);
                }
            }
            //bc always this separation from headers
            fileArr.RemoveAt(1);
            return fileArr;
        }

        static List<string[]> JSONread (string input)
        {
            if(verboseMode) Console.WriteLine("Reading JSON file");
            List<string[]> fileArr = new List<string[]>();
            //string pattern = @"^['""]?(.*?)['""]?$";

            //string line = new StreamReader(input);
            using (StreamReader sr = new StreamReader(input))
            {
                string line;
                List<string> properties = new List<string>();
                
            //get properties
                while((line = sr.ReadLine()) != "}")
                {
                    if(line == "[" || line == "{" || line == "]")
                    {
                        continue;
                    }
                    string [] arr = line.Split(":");
                    arr[0] = Regex.Replace(arr[0],@"['""]", "");
                    properties.Add(arr[0]);
                }

                //add string[] of properties to List fileArr
                string [] prop = properties.ToArray();
                fileArr.Add(prop);

                //restart StreamReader
            sr.DiscardBufferedData();

                List<string> values = new List<string>();
                while((line = sr.ReadLine()) != null)
                {
                    if(line == "[" || line == "{" || line == "]")
                    {
                        continue;
                    }
                    if(line.ToCharArray()[0] == '}')
                    {
                        string [] val = values.ToArray();
                        fileArr.Add(val);
                        values.Clear();
                    }
                    else
                    {
                    string [] arr = line.Split(":");
                    arr[1] = Regex.Replace(arr[1],@"['"",]", "");
                    values.Add(arr[1]);
                    }
                }
            }
            return fileArr;
        }

        static List<string[]> HTMLread (string input)
        {
            if(verboseMode) Console.WriteLine("Reading HTML file");
            List<string[]> fileArr = new List<string[]>();

            using (StreamReader sr = new StreamReader(input))
            {
                string line;
                List<string> properties = new List<string>();
                
            //get properties
                while((line = sr.ReadLine()) != "</tr>")
                {
                    if(line == "<table>" || line == "<tr>")
                    {
                        continue;
                    }
                    line = Regex.Replace(line,@"[<th.*>]", "");
                    line = Regex.Replace(line, @"</th", "");
                    properties.Add(line);
                }

                //add string[] of properties to List fileArr
                string [] prop = properties.ToArray();
                fileArr.Add(prop);

                List<string> values = new List<string>();
                while((line = sr.ReadLine()) != null)
                {
                    if(line == "<tr>" || line == "</table>")
                    {
                        continue;
                    }
                    if(line == "</tr>")
                    {
                        string [] val = values.ToArray();
                        for(int i = 0; i<val.Length; i++)
                        {
                            val[i] = val[i].Trim();
                        }                            
                    fileArr.Add(val);
                    values.Clear();
                    }
                    else
                    {
                    line = Regex.Replace(line,@"[<td.*>]","");
                    line = Regex.Replace(line, @"[</td>]", "");
                    values.Add(line);;
                    }
                }
            }
            return fileArr;
        }

        static void CSVwrite (List<string[]> myList, string output)
        {
            if(verboseMode) Console.WriteLine("Writing CSV file");

            System.IO.File.WriteAllText(output, string.Empty);
            using (StreamWriter sw = File.CreateText(output))
            {  
                //add to every element quotation marks          
                for(int i = 0; i<myList.Count; i++)
                {
                    for(int j = 0; j<myList[i].Length; j++)
                    {
                        myList[i][j] = "\"" + myList[i][j] +  "\"";
                        sw.Write(myList[i][j] + ",");
                    }
                    sw.WriteLine();
                }
            }
            Console.WriteLine("Wrote succesfully");
        }

        static void MDwrite (List<string[]> myList, string output)
        {
            if(verboseMode) Console.WriteLine("Writing MD file");

            System.IO.File.WriteAllText(output, string.Empty);
            using (StreamWriter sw = File.CreateText(output))
            {  
                int[] lengths = new int [myList[0].Length];

                //to know lengths of the longest word in the column
                for(int i = 0; i<myList.Count; i++)
                {
                    for(int k = 0; k<myList[i].Length; k++)
                    {
                        lengths[k] = Math.Max(lengths[k], myList[i][k].Length);
                    }
                }
                for(int i = 0; i<myList.Count; i++)
                {
                    if(i == 1)
                    {
                        for(int z = 0; z < myList[0].Length; z++)
                        {
                            sw.Write("|");
                            for(int x = 0; x<lengths[z]; x++)
                            {
                                sw.Write("-");
                            }
                        }
                        sw.Write("|");
                        sw.WriteLine();
                    }
                    //to add | 
                    for(int j = 0; j<myList[i].Length; j++)
                    {
                        myList[i][0] = "|" + myList[i][0];
                        Console.WriteLine(lengths[j]);
                        myList[i][j] = String.Format("{0," + -lengths[j] + "}",myList[i][j]) + "|";
                        sw.Write(myList[i][j]);
                    }
                    sw.WriteLine();
                }
            }
            Console.WriteLine("Wrote succesfully to the file");
        }

        static void JSONwrite (List<string[]> myList, string output)
        {
            System.IO.File.WriteAllText(output, string.Empty);
            if(verboseMode) Console.WriteLine("Writing JSON file");
            using (StreamWriter sw = File.CreateText(output))
            {
                sw.WriteLine("[");

                for(int i = 1; i<myList.Count;i++)
                {
                    sw.WriteLine(String.Format("{0,2}", "{"));

                    for(int j = 0; j<myList[i].Length;j++)
                    {
                        //property
                        //value
                        //check if not digit
                        if(!Regex.IsMatch(myList[i][j], @"^\d+$"))
                        {
                            myList[i][j] = "\"" + myList[i][j] +  "\"";
                        }
                        //if not the last value of the object add semicolon
                        if(j!=myList[i].Length-1)
                        {
                            myList[i][j] = myList[i][j] + ",";
                        }
                        sw.WriteLine("    " + myList[0][j] + ": " + myList[i][j]);
                    }
                    sw.Write(String.Format("{0,2}", "}"));
                    if(i!=myList.Count-1)
                    {
                        sw.WriteLine(",");
                    }
                    sw.WriteLine();
                }
                sw.WriteLine("]");
            }
            Console.WriteLine("Wrote succesfully to the file");
        }
        
        static void  HTMLwrite (List<string[]> myList, string output)
        {
            if(verboseMode) Console.WriteLine("Writing HTML file");

            System.IO.File.WriteAllText(output, string.Empty);
            string headerOrDataBeg = "td";
            string headerOrDataEnd = "td";
            using (StreamWriter sw = File.CreateText(output))
            {
                sw.WriteLine("<table>");

                for(int i = 0; i<myList.Count;i++)
                {
                    sw.WriteLine(String.Format("{0,4}", "<tr>"));

                    for(int j = 0; j<myList[i].Length;j++)
                    {
                        if(i == 0)
                        {
                            headerOrDataBeg = "th";
                            headerOrDataEnd = "th";
                        }
                        if(Regex.IsMatch(myList[i][j], @"^\d+$"))
                        {
                            headerOrDataBeg = @"td align=""right""";
                        }
                        myList[i][j] = "<" + headerOrDataBeg + ">" + myList[i][j] + "</" + headerOrDataEnd + ">";
                        sw.WriteLine(String.Format("        " + myList[i][j]));
                        headerOrDataBeg = "td";
                    }
                    sw.WriteLine(String.Format("{0,4}", "</tr>"));
                }
                sw.WriteLine("</table>");
            }
            Console.WriteLine("Wrote succesfully to the file");
        }
    }


    class Program
    {
        static void Main(string[] args)
        {
            Tabular tabconv = new Tabular(args);
            tabconv.checkCLI();
        }
    }
}


import os
import sys,re
def walkDir():
    pat2 = re.compile('public class')
    filePaths=[]
    outfile = open('classDia.java', 'w')
    for root, dirs, files in os.walk('./'):
        for name in files:       
            filename = os.path.join(root, name)
            parts=filename.split('.')
            size=len(parts)
            indexExt=size-1
            if parts[indexExt]=='java':
                filePaths.append(filename)
            #filePaths.append(filename)
            #absfilename=os.path.abspath(name)
            #s = filename + '\n'
            #f.write(s)
            #print filename
    for filePath in filePaths:
        curFile = open(filePath,'r')
        for line in curFile:
            if pat2.search(line):
                outString,count = re.subn(pat2,'class',line)
                outfile.write(outString)
            else:
                outfile.write(line)
            #f.write(line)
        curFile.close()        
    outfile.close()


if __name__=="__main__":
    walkDir()       



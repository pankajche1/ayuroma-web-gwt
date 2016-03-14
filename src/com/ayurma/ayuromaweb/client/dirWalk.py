import os

def walkDir():
    f = open('myfile.txt', 'w')
    for root, dirs, files in os.walk('./'):
        for name in files:       
            filename = os.path.join(root, name)
            absfilename=os.path.abspath(name)
            s = filename + '\n'
            f.write(s)
            #print filename
    

if __name__=="__main__":
    walkDir()       



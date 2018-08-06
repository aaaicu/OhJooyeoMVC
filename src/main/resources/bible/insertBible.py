
# coding: utf-8
import codecs
import locale

import os
import re
import pymysql.cursors

conn = pymysql.connect(
        host='ec2-13-125-210-249.ap-northeast-2.compute.amazonaws.com',
        user='dev',
        password='ohjooyeo1!',
        db = 'ohjooyeo',
        charset='utf8' )

file_list = []
print(os.walk("/Users/kyome/dev/workspace/OhJooYeo/src/main/resources/bible/bible_ohjooyeo"))
for (path, dir, files) in os.walk("/Users/kyome/dev/workspace/OhJooYeo/src/main/resources/bible/bible_ohjooyeo"):
    for filename in files:
        # print(filename)
        ext = os.path.splitext(filename)[-1]
        if ext == '.txt':
            file_list.append(path +"/"+ filename)
            # print("%s/%s" % (path, filename))
print(file_list)

for dir in file_list :
    f = codecs.open(dir, 'r', encoding='cp949')
    temp = dir.split('/')
    book = temp[len(temp)-1]
    print("chk" ,book)
    print(type(book))
    book = re.sub("[a-zA-Z]","",book)
    book = re.sub("\d","",book)
    book = re.sub("\W","",book)
    print(book)
    while True:
        line = f.readline()
        if not line : break
        ch_se = re.search("\w[0-9]*\:[0-9]*", line).group()
        ch_se = re.search("[0-9].*[0-9]", ch_se).group()
        chapter = ch_se.split(  ":")[0]
        section = ch_se.split(":")[1]

        print("정규식 : " + ch_se)

        section = section.replace(":", "")
        phrase = re.search("\ .*",line).group()
        if re.search("\>.*",phrase) != None :
            phrase = re.search("\>.*",phrase).group()
            phrase = phrase.replace(">","")
        phrase = phrase.strip()
        # try:
        with conn.cursor() as cursor:
            sql = 'INSERT INTO BIBLE VALUES (trim(%s),%s,%s,%s)'
            print("book : "+book)
            print("chapter : " +  chapter)
            print("section : "+ section)
            print("phrase : "+ phrase)
            print("chk" , book)
            cursor.execute(sql, (book,chapter,section,phrase))
            conn.commit()
        # except Exception as ex:
        print(line)
        print(chapter)
        print(section)
        print(phrase)
    f.close()

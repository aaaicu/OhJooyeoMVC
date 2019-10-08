
import codecs
import locale

import os
import re
import pymysql.cursors
# import io, sys
# sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf8')

conn = pymysql.connect(
        host='aaaicu.synology.me',
        # port='3306',
        user='dev_ohjooyeo',
        password='ohjooyeo1!',
        db = 'ohjooyeo',
        charset='utf8' )

file_list = []
# print(os.walk("/Users/kyome/dev/workspace/OhJooYeo/src/main/resources/bible/bible_ohjooyeo"))
for (path, dir, files) in os.walk("/Users/kyome/dev/workspace/OhJooYeo/src/main/resources/bible/bible_ohjooyeo"):
    for filename in files:
        # print(filename)
        ext = os.path.splitext(filename)[-1]
        if ext == '.txt':
            file_list.append(path +"/"+ filename)
            # print("%s/%s" % (path, filename))
# print(file_list)

for dir in file_list :
    temp = dir.split('/')
    book = temp[len(temp)-1]
    if book[0] == "1":
        version = "old"
    else:
        version = "new"

    book = re.sub("[a-zA-Z]","",book)
    book = re.sub("\d","",book)
    book = re.sub("\W","",book)
    test = book.encode('utf-8')
    print(book.encode('utf-8'))
    print(test.decode('utf-8'))
    print(len(test.decode('utf-8')))
    # print(book)
    # print(version)
    # print(type(book))
#
for dir in file_list :

    f = codecs.open(dir, 'r', encoding='cp949')
    temp = dir.split('/')
    book = temp[len(temp)-1]
    if book[0] == "1":
        version = "old"
    else:
        version = "new"

    order = str(int(book[2:4]))

    book = re.sub("[a-zA-Z]","",book)
    book = re.sub("\d","",book)
    book = re.sub("\W","",book)

    # str(book, "utf-8")
    print("")
    print(book)
    print(len(book))
    print(book.encode('utf-8'))
    print(book.encode('utf-8').decode('utf-8'))
    print(len(book.encode('utf-8').decode('utf-8')))

    print("고린도후서")
    print(len("고린도후서"))
    print("고린도후서".encode('utf-8'))
    while True:
        line = f.readline()
        if not line : break
        ch_se = re.search("\w[0-9]*\:[0-9]*", line).group()
        ch_se = re.search("[0-9].*[0-9]", ch_se).group()
        chapter = ch_se.split(  ":")[0]
        section = ch_se.split(":")[1]

        # print("정규식 : " + ch_se)

        section = section.replace(":", "")
        phrase = re.search("\ .*",line).group()
        if re.search("\>.*",phrase) != None :
            phrase = re.search("\>.*",phrase).group()
            phrase = phrase.replace(">","")
        phrase = phrase.strip()
        # try:
        with conn.cursor() as cursor:

            # sql = 'INSERT INTO BIBLE(book,chapter,section,contents,BIBLE.order, testament) VALUES (trim(%s),%s,%s,%s,%s,%s)'
            sql = "INSERT INTO BIBLE(book,chapter,section,contents,BIBLE.order, testament) VALUES (trim('"\
                  + book+"'),"+chapter+","+section+",'"+phrase+"',"+order+",'"+version+"')"
            # print("book : "+book)
            # print("chapter : " +  chapter)
            # print("section : "+ section)
            # print("phrase : "+ phrase)
            # print("order : " + order)
            print(sql)
            cursor.execute(sql.encode('utf8'))
            conn.commit()
        # except Exception as ex:
        # print(line)
        # print(chapter)
        # print(section)
        # print(phrase)
    f.close()

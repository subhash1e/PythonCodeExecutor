from os.path import dirname, join
import sys
# import main2_
from io import StringIO
res = sys.stdout
my_stdout = sys.stdout = StringIO()


def x_x():
    filename = join(dirname(__file__), "main2_.py")
    sys.stdout = res
    my_stdout = sys.stdout = StringIO()
    exec(open("/data/data/com.subhash1e.pythoncodeexecutor/files/main_2.py").read())
    return my_stdout.getvalue()


# print("hello world")



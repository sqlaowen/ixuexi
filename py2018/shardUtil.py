import math


# 取hash码
def convert_n_bytes(n, b):
    bits = b * 8
    return (n + 2 ** (bits - 1)) % 2 ** bits - 2 ** (bits - 1)


def convert_4_bytes(n):
    return convert_n_bytes(n, 4)


def getHashCode(s):
    h = 0
    n = len(s)
    for i, c in enumerate(s):
        h = h + ord(c) * 31 ** (n - 1 - i)
    return convert_4_bytes(h)


def getSharding(str1):
    # hashCode
    hc = getHashCode(str1)
    # 绝对值
    hcAbs = math.fabs(hc)
    # 分表结果
    return int(hcAbs % 32)


if __name__ == '__main__':
    print(getSharding('1'))

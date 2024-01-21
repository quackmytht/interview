## 第一题

1). 编写一个shell脚本(linux)，功能如下： 在给定文件中搜索指定内容，并将搜索结果(含内容出现的行号)保存到新的文件中，同时结果输出到控制台

```shell
#!/bin/bash

# 检查参数是否正确
if [ "$#" -ne 3 ]; then
  echo "用法: $0 <文件路径> <搜索内容> <结果文件>"
  exit 1
fi

# 获取参数
file_path=$1
search_content=$2
result_file=$3

# 搜索内容并将结果保存到文件
grep -n "$search_content" "$file_path" > "$result_file"

# 输出结果到控制台
cat "$result_file"

echo "搜索完成，结果保存在 $result_file 中。"

```

> 执行下面命令添加权限

<kbd>chmod +x search.sh</kbd>

> 使用方式

<kbd>./search.sh [文件路径] [搜索内容] [结果文件]</kbd>

## 第二题

```shell
#!/bin/bash

# 显示硬盘使用情况
echo "硬盘使用情况："
df -h

# 显示内存使用情况
echo -e "\n内存使用情况："
free -h

# 显示CPU使用情况
echo -e "\nCPU使用情况："
top -b -n 1 | grep "%Cpu"

# 若要显示更多详细的CPU信息，可以使用以下命令：
# mpstat

# 若要显示更多详细的内存信息，可以使用以下命令：
# cat /proc/meminfo

```

> 执行下面命令添加权限

<kbd>chmod +x system_status.sh</kbd>

> 使用方式

<kbd>./system_status.sh</kbd>

package com.xiaopan.thread.unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe
 * @ClassName: _10_Serializable
 * @date: 2018��3��19�� ����9:35:30
 * @Description: �������л�
 * ÿ���˶�֪��java��׼�����л��Ĺ����ٶȺ�������������Ҫ��ӵ�й��еĹ��캯����

�ⲿ���л��Ǹ��õķ�ʽ��������Ҫ������Դ����л����schema��

�ǳ����еĸ��������л��⣬��kryo����ʹ�����Ƶģ��������ڴ�ȱ���Ļ����Ͳ����ʡ�

��ͨ��ʹ��Unsafe�����ǿ��Էǳ��򵥵�ʵ�����������л����ܡ�

���л���

ͨ�����䶨��������л��� �������ֻ��һ�Ρ�
ͨ��Unsafe��getLong, getInt, getObject�ȷ�����ȡ�ֶ���ʵ��ֵ��
��ӿ��Իָ��ö���ı�ʶ����
����Щ����д�뵽���
��ȻҲ����ʹ��ѹ������ʡ�ռ䡣

�����л�:

����һ�����л����ʵ��������ͨ������allocateInstance����Ϊ�÷�������Ҫ�κι��췽����
����schama, �����л�����
���ļ��������ȡ���е��ֶ�
ʹ�� Unsafe �� putLong, putInt, putObject�ȷ�����������
Actually, there are much more details in correct inplementation, but intuition is clear.

��ʵ��Ҫ��ȷʵ�����л��ͷ����л���Ҫע��ܶ�ϸ�ڣ�����˼·�������ġ�

�������л���ʽ�Ƿǳ���ġ�

˳��˵һ�䣬�� kryo �����ʹ��Unsafe�ĳ��� http://code.google.com/p/kryo/issues/detail?id=75
 */
public class _10_Serializable {

}

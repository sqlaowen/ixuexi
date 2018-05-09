from django.shortcuts import render
from django.http import HttpResponse
from mgrapp import ExportDataUtil


def index(request):
    databaseId = 0
    sqlFmt = ''
    sqlCountFmt = ''
    erpName = ''
    erpPassword = ''
    path = ''

    if 'POST' == request.method:
        databaseId = int(request.POST.get('databaseId')) if '' != request.POST.get('databaseId') else 0
        sqlFmt = request.POST.get('sqlFmt')
        sqlCountFmt = request.POST.get('sqlCountFmt')
        erpName = request.POST.get('erpName')
        erpPassword = request.POST.get('erpPassword')
        path = ExportDataUtil.exportData(sqlFmt, sqlCountFmt, databaseId, erpName, erpPassword)
    context = {}

    if '' != path:
        arr1 = path.split('/')
        fileName = arr1[len(arr1) - 1]
        path = '/static/files/' + fileName
    context['filePath'] = path
    return render(request, 'index.html', context)

export default {
  methods: {
    downloadOnclick() {

      // let blob = new Blob()
      // ([res.data],{
      //   type: headers['content-type']
      //   })
      // debugger
      // window.open(this.url)
      // window.open(this.url, '_parent', 'width=200,height=100')
      // window.open('page.html', 'newwindow', 'height=100, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
      // const url = 'C:\\Users\\Administrator\\Desktop\\01-傅超-莱克B2B事业部-2019半年度述职报告.pptx'
      //   const link = document.createElement('a')
      //   link.style.display = 'none'
      //   link.href = url
      //   link.setAttribute(
      //     'download',
      //     '导入学生账号模板'
      //   )
      //   document.body.appendChild(link)
      //   link.click()

      // window.location.href = this.url
      window.open(this.url)
    }
  },
  data() {
    return {
      // url: 'http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg'
    url:'./index.js'
    }
  }
}
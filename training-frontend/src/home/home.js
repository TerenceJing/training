import { menuList } from './menu.js'
export default {
  created: function () {
    this.init()
  },
  data() {
    return {
      menuList: menuList,
      menuName: new Date(),
      detailName: '你好'
    }
  },
  methods: {
    init() {
    },
    handleSelect(key, keyPath) {      
      for (var i = 0; i < this.menuList.length; i++) {
        if (this.menuList[i].index === keyPath[0]) {
          this.menuName = this.menuList[i].name
          var child = this.menuList[i].children
          for (var j = 0; j < child.length; j++) {
            if ((this.menuList[i].index + '.' + child[j].index) === keyPath[1]) {
              this.detailName = child[j].name
              this.$router.push(child[j].path)
            }
          }
        }
      }
    }  
  }
}

import { depList, orgList, userList } from '../modelData'
export default {
  name: 'component',
  data() {
    return {
      reFresh:true,
      isdisabled: true,
      depList: depList,
      orgList: orgList,
      userList: userList,
      props: {
        label: 'name',
        children: 'zones',
        isLeaf: 'leaf'
      },
      // props：标签绑定名称，用于映射node中属性名和data对象属性名
      idArr:[]
    }
  },
  methods: {
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    },
    loadNode(node, resolve) {      
      if (node.level === 0) {
        return resolve(depList)
      } else if (node.level === 1) {
        setTimeout(() => {
          resolve(this.orgList);
        }, 200)
      } else if (node.level === 2) {
        setTimeout(() => {
          resolve(this.userList);
        }, 200)
      } else {
        return resolve([]);
      }
    },
    renderContent(h, { node, data, store }) {      
      return (
        <span>
          {this.showName(data)}
          <span style="color:white">{"----"}</span>
          {this.isShowAdd(node, data)}
          {this.isShowConfirm(node,data)}
          <el-button type="text" icon="el-icon-edit" on-click={() => this.showEdit(h, { node, data })} />
          <el-button type="text" icon="el-icon-delete" on-click={() => this.deleteOnclick(node, data)} />
        </span>
      )
    },
    showName(data) {
      if (data.isAdding) {
      } else if (data.isEditing) {
        return (
          <span> <el-input size="mini" style="width:100px;height:20px;padding:0;" />
          </span>)
      } else {
        return (<span> {data.name}</span>)
      }
    },
    showEdit(h, { node, data }) {
      debugger
      data.isEditing = true
      this.refresh(node)
    },
    blur(ev, data) {
      return (<span>悬停</span>)
    },
    isShowAdd(node, data) {
      if (!data.leaf && !data.isEditing) {
        return (
          <el-button v-if="node.isLeaf" type="text" icon="el-icon-plus" on-click={() => this.addChildOnclick(node, data)} />
        )
      } else {
        return
      }
    },
    addChildOnclick(node, data) {
      debugger
      // node.childNodes.push({})
      if (node.level === 1) {
        node.childNodes.push({})
        this.orgList.push({ name: '组织', leaf: false, isEditing: true })
      } else if (node.level === 2) {
        this.userList.push({ name: '叶子', leaf: true, isEditing: true })
      }
      this.refresh(node)
    },
    isShowConfirm(node,data) {
      if (data.isEditing) {
        return (
          <el-button
            size="mini"
            style="width:40px;height:20px;padding:0;font-size:10px;background:rgb(68,133,151);color:white;"
            on-click={ev => this.submit(ev, data)}
          >
            确定</el-button>
        )
      } else {
        return
      }
    },
    submit(ev, data) {
      data.isEditing = false
      this.refresh()
    },
    deleteOnclick(node, data) {
      debugger
      var parent = node.parent
      var children = parent.childNodes
      var copyChildren = []
      for (var i = 0; i < children.length; i++) {
        if (children[i].id !== node.id) {
          copyChildren.push(children[i])
        }
      }
      parent.childNodes = copyChildren
      // this.refresh()
    },
    refresh(node){
      this.reFresh=false
      debugger
      this.idArr.push(node.id)
      this.$nextTick(()=>{                    
        this.reFresh = true
    })
    },
    handleNodeClick(data,node,it){
      debugger
      // this.refresh(node)
    }
  }
}

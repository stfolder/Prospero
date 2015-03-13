package sstar.prospero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import sstar.prospero.dao.OperationDAO;
import sstar.prospero.entity.Form;
import sstar.prospero.entity.Operation;
import sun.reflect.generics.tree.Tree;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey.Tarasenko on 08.03.2015.
 * * 
 */

@Path("operation")
public class OperationRS extends SpringResource{
        private static  final int MAX_TREE_DEEP = 5;
    @Autowired 
    OperationDAO operationDAO;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tree")
    public List<TreeItem> getTreeData(){
            List<TreeItem> items = constructTree(null,0);
            return items;
    }
        
    private TreeItem operationToTreeItem(Operation op){
            TreeItem ti = new TreeItem();
            ti.setKey(""+op.getOperationId());
            ti.setTitle(op.getCaption());
            ti.setFolder(true);
            return ti;
    }

        private TreeItem formToTreeItem(Form form){
                TreeItem ti = new TreeItem();
                ti.setKey(""+form.getFormId());
                ti.setTitle(form.getCaption());
                ti.setFolder(false);
                return ti;
        }

        private List<TreeItem> constructTree(Operation parent, int deep) {
                Integer parentId = null;
                List<TreeItem> items = new ArrayList<TreeItem>();
                if(parent!=null) {
                        parentId = parent.getOperationId();
                }
                List<Operation> operations = operationDAO.getOperations(parentId);
                for(Operation op:operations) {
                        TreeItem ti = operationToTreeItem(op);
                        items.add(ti);
                        
                        List<Form> forms = operationDAO.getForms(op.getOperationId());

                        if(deep<=MAX_TREE_DEEP) {
                                ti.setChildren(constructTree(op, ++deep));
                        }
                        List<TreeItem> fitimes = new ArrayList<TreeItem>();
                        for(Form form:forms) {
                                fitimes.add(formToTreeItem(form));
                        }
                        if(fitimes.size()>0){
                                fitimes.addAll(ti.getChildren());
                                ti.setChildren(fitimes);
                        }
                }
                return items;
        }
        
}

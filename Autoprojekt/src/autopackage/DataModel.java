package autopackage;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class DataModel extends ListDataModel<Auto> implements SelectableDataModel<Auto> {

	public DataModel() {
	}

	public DataModel(List<Auto> data) {
		super(data);
	}

	@Override
	public Auto getRowData(String rowKey) {
		int nRowKey = Integer.valueOf(rowKey);
		@SuppressWarnings("unchecked")
		List<Auto> autos = (List<Auto>) getWrappedData();
		for (Auto auto : autos) {
			if (auto.getAutoid() == nRowKey)
				return auto;
		}
		return null;
	}

	@Override
	public Object getRowKey(Auto a) {
		return a.getAutoid();
	}
}